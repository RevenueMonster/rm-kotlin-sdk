package io.revenuemonster.sdk.util

import io.ktor.util.*
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
        val plainText: String = if (data != "") {
            (
                    "data=" + encodedData + "&method=" + method.lowercase() + "&nonceStr=" +
                            nonceStr + "&requestUrl=" + requestUrl + "&signType=" + signType +
                            "&timestamp=" + timestamp
                    )
        } else {
            (
                    "method=" + method.lowercase() + "&nonceStr=" +
                            nonceStr + "&requestUrl=" + requestUrl + "&signType=" + signType +
                            "&timestamp=" + timestamp
                    )
        }
        val plainTextByte = plainText.toByteArray()
        var pKey: PrivateKey? = null
        if (privateKey.contains("-----BEGIN PRIVATE KEY-----")) {
            pKey = readPCKS1Key(privateKey)
        } else if (privateKey.contains("-----BEGIN RSA PRIVATE KEY-----")) {
            pKey = readPCKS1Key(privateKey)
        }
        val sig = Signature.getInstance("SHA256WithRSA")
        sig.initSign(pKey)
        sig.update(plainTextByte)
        val signatureBytes = sig.sign()
        return signatureBytes.encodeBase64()
    }

    private fun readPCKS1Key(key: String): PrivateKey {
        val content =
            key.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replace("\\n".toRegex(), "")
                .replace("\\s".toRegex(), "")

        val kf = KeyFactory.getInstance("RSA")
        val keySpecPKCS8 = PKCS8EncodedKeySpec(content.decodeBase64Bytes())
        return kf.generatePrivate(keySpecPKCS8)
    }

}