package io.revenuemonster.sdk.model.response

import io.revenuemonster.sdk.model.common.Store
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class GetTransactionResponse(
    val store: Store,
    val referenceId: String,
    val transactionId: String,
    val order: GetTransactionOrder,
    val terminalId: String,
    val currencyType: String,
    val balanceAmount: Int,
    val platform: String,
    val method: List<String>,
    val error: String,
    val transactionAt: Instant,
    val type: String,
    val status: String,
    val region: String,
    val createdAt: Instant,
    val updatedAt: Instant
)

@Serializable
data class GetTransactionOrder(
    val id: String,
    val title: String,
    val detail: String,
    val additionalData: String,
    val amount: Int
)
