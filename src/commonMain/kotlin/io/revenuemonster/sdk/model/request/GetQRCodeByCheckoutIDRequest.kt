package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class GetQRCodeByCheckoutIDRequest(
    val checkoutId: String,
    val method: String,
    val type: String = "QRCODE"
)