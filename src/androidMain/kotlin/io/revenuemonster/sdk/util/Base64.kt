package io.revenuemonster.sdk.util

import io.ktor.util.*

actual object Base64Factory{
    actual fun createEncoder() : Base64Encoder = AndroidBase64Encoder
}

object AndroidBase64Encoder : Base64Encoder{
    @OptIn(InternalAPI::class)
    override fun encode(src: ByteArray): ByteArray {
        return src.encodeBase64().toByteArray()
    }
}