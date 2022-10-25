package io.revenuemonster.sdk.model.request

import io.revenuemonster.sdk.model.common.Order
import kotlinx.serialization.Serializable

@Serializable
data class PosQuickPay(
    val terminalId: String,
    val type: String,
    val provider: String? = null,
    val receiptType: Int,
    val cameraType: String,
    val order: Order
)