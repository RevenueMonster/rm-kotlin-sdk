package io.revenuemonster.sdk.model.response

import io.revenuemonster.sdk.model.common.OrderResponse
import io.revenuemonster.sdk.model.common.Store
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class GetTransactionResponse(
    val store: Store,
    val referenceId: String,
    val transactionId: String,
    val order: OrderResponse,
    val terminalId: String,
    val currencyType: String,
    val balanceAmount: Int,
    val platform: String,
    val method: String,
    val error: TransactionError? = null,
    val transactionAt: Instant,
    val type: String,
    val status: String,
    val region: String,
    val source : String,
    val createdAt: Instant,
    val updatedAt: Instant
)

@Serializable
data class TransactionError(
    val message: String
)

