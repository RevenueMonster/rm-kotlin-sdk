package io.revenuemonster.sdk.util

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
        TODO("Not yet implemented")
    }
}
