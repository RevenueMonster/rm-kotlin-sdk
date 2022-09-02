package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class PosCardRefund(
    val terminalId: String,
    val type: String,
    val data: PosCardRefundData
)

@Serializable
data class PosCardRefundData(
    val transactionId: String,
    val receiptType: Int,
    val reason: String,
    val email: String,
    val pin: String,
)
