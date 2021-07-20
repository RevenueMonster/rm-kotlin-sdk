package io.revenuemonster.sdk.model.response

import kotlinx.serialization.Serializable

@Serializable
data class GetQRCodeByCheckoutIDResponse(
    val type: String,
    val qrCode : QRData
)

@Serializable
data class QRData(
    val base64Image: String,
    val data: String,
)