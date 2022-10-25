package io.revenuemonster.sdk.util

import io.ktor.util.*
import java.nio.ByteBuffer
import java.security.GeneralSecurityException
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec

actual object Signature {
    actual fun generateSignature(
        data: String,
        privateKey: String,
        requestUrl: String,
        nonceStr: String,
        signType: String,
        method: String,
        timestamp: String
    ): String {
        val encodedData: String = data.encodeBase64()
        val plainText: String = if (data.isNotEmpty()) {
            "data=$encodedData&method=${method.lowercase()}&nonceStr=$nonceStr&requestUrl=$requestUrl&signType=$signType&timestamp=$timestamp"
        } else {
            "method=${method.lowercase()}&nonceStr=$nonceStr&requestUrl=$requestUrl&signType=$signType&timestamp=$timestamp"
        }
        val plainTextByte = plainText.toByteArray()

        var pKey: PrivateKey? = null
        if (privateKey.contains("-----BEGIN PRIVATE KEY-----")) {

            val dataString = privateKey
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replace("\\n".toRegex(), "")
                .replace("\\s".toRegex(), "")

            pKey = readPKCS8Key(dataString.decodeBase64Bytes())

        } else if (privateKey.contains("-----BEGIN RSA PRIVATE KEY-----")) {

            val dataString = privateKey
                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "")
                .replace("\\n".toRegex(), "")
                .replace("\\s".toRegex(), "")

            pKey = readPCKS1Key(dataString.decodeBase64Bytes())

        }

        val sig = Signature.getInstance("SHA256WithRSA")
        sig.initSign(pKey)
        sig.update(plainTextByte)
        val signatureBytes = sig.sign()
        return signatureBytes.encodeBase64()
    }

    @Throws(GeneralSecurityException::class)
    private fun readPCKS1Key(bytes: ByteArray): PrivateKey {
        val pkcs1Length = bytes.size
        val totalLength = pkcs1Length + 22
        val pkcs8Header = byteArrayOf(
            0x30,
            0x82.toByte(),
            (totalLength shr 8 and 0xff).toByte(),
            (totalLength and 0xff).toByte(),
            0x2,
            0x1,
            0x0,
            0x30,
            0xD,
            0x6,
            0x9,
            0x2A,
            0x86.toByte(),
            0x48,
            0x86.toByte(),
            0xF7.toByte(),
            0xD,
            0x1,
            0x1,
            0x1,
            0x5,
            0x0,
            0x4,
            0x82.toByte(),
            (pkcs1Length shr 8 and 0xff).toByte(),
            (pkcs1Length and 0xff).toByte()
        )

        val pkcs8bytes = ByteBuffer.allocate(pkcs8Header.size + bytes.size)
            .put(pkcs8Header)
            .put(bytes)
            .array()
        return readPKCS8Key(pkcs8bytes)
    }

    private fun readPKCS8Key(bytes: ByteArray): PrivateKey {
        val kf = KeyFactory.getInstance("RSA")
        val keySpecPKCS8 = PKCS8EncodedKeySpec(bytes)
        return kf.generatePrivate(keySpecPKCS8)
    }

}