package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class PosSettlement(
    val terminalId: String,
    val type: String,
    val data: PosSettlementData
)

@Serializable
data class PosSettlementData(
    val receiptType: Int,
    val provider: String? = null
)