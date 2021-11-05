package io.revenuemonster.sdk.model.response

import io.revenuemonster.sdk.model.common.OrderResponse
import io.revenuemonster.sdk.model.common.Payee
import io.revenuemonster.sdk.model.common.Store
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val store: Store?=null,
    val referenceId: String,
    val transactionId: String,
    val order: OrderResponse,
    val terminalId: String,
    val payee: Payee? = null,
    val currencyType: String,
    val balanceAmount: Int,
    val platform: String,
    val method: String,
    val transactionAt: Instant,
    val type: String,
    val status: String,
    val region: String,
    val createdAt: Instant,
    val updatedAt: Instant
)

