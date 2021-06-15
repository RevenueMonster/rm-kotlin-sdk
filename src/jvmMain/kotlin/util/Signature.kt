package org.rm.sdk.util

import java.nio.charset.StandardCharsets
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*

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
        var result = ""
        try {
            val encodedData: String = encodeBase64(data)
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
            println("Text => $plainText")
            val plainTextByte = plainText.toByteArray(StandardCharsets.UTF_8)
            var privKey: PrivateKey? = null
            if (privateKey.contains("-----BEGIN PRIVATE KEY-----")) {
                privKey = readPCKS1Key(privateKey)
            } else if (privateKey.contains("-----BEGIN RSA PRIVATE KEY-----")) {
                privKey = readPCKS1Key(privateKey)
            }
            val sig = Signature.getInstance("SHA256WithRSA")
            sig.initSign(privKey)
            sig.update(plainTextByte)
            val signatureBytes = sig.sign()
            result = String(Base64.getEncoder().encode(signatureBytes))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return result
    }

    private fun encodeBase64(plainText: String): String {
        val encoded = Base64.getEncoder().encode(plainText.toByteArray())
        return String(encoded)
    }

    private fun decodeBase64(plainText: String): String {
        val decoded = Base64.getDecoder().decode(plainText.toByteArray())
        return String(decoded)
    }

    private fun readPCKS1Key(key: String): PrivateKey {
        val content =
            key.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replace("\\n".toRegex(), "")
                .replace("\\s".toRegex(), "")

        val kf = KeyFactory.getInstance("RSA")
        val keySpecPKCS8 = PKCS8EncodedKeySpec(Base64.getDecoder().decode(content))
        return kf.generatePrivate(keySpecPKCS8)
    }

    private fun readPKCS8Key(key: String): PrivateKey {
        val content = key
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replace("\\n", "")
            .replace("\\s", "")
        val bytes = Base64.getDecoder().decode(content)
        val keySpec = PKCS8EncodedKeySpec(bytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        return keyFactory.generatePrivate(keySpec)
    }
}
