package org.rm.sdk.util

expect object Signature {
    fun generateSignature(
        data: String,
        privateKey: String,
        requestUrl: String,
        nonceStr: String,
        signType: String,
        method: String,
        timestamp: String
    ): String
}
