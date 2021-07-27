package io.revenuemonster.sdk.model.response

import kotlinx.serialization.Serializable

@Serializable
data class QRUrl(
    val id: String? = null,
    val qrCodeUrl: String
)
