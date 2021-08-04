package io.revenuemonster.sdk.model.response

import kotlinx.serialization.Serializable

@Serializable
data class GetQRCodeByCheckoutIDResponse(
    val type: String,
    val qrcode: QRData
)

@Serializable
data class QRData(
    val base64Image: String,
    val data: String,
)