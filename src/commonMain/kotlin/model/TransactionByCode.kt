package org.rm.sdk.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

//Get Transaction By Code
@Serializable
data class TransactionByCode(
    val store: Store,
    val referenceId: String,
    val transactionId: String,
    val order: Order,
    val terminalId: String,
    val currencyType: String,
    val balanceAmount: UInt,
    val platform: String,
    val method: String,
    val error: String,
    val transactionAt: Instant,
    val type: String,
    val status: String,
    val region: String,
    val createAt: Instant,
    val updateAt: Instant
)