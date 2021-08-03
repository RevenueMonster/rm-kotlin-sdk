package io.revenuemonster.sdk.util

import io.ktor.util.*

actual object Base64Factory {
    actual fun createEncoder(): Base64Encoder = JvmBase64Encoder
}

object JvmBase64Encoder : Base64Encoder {
    @InternalAPI
    override fun encode(src: ByteArray): ByteArray =  src.encodeBase64().toByteArray()
}
