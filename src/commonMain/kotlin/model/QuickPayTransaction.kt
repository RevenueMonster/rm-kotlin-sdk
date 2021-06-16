package org.rm.sdk.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

//quick pay response
@Serializable
data class QuickPayTransaction(
    val store: Store,
    val referenceId: String,
    val transactionId: String,
    val order: QuickPayOrder,
    val payee: Payee,
    val currencyType: String,
    val balanceAmount: UInt,
    val platform: String,
    val method: String,
    val type: String,
    val status: String,
    val region: String,
    val error : String,
    val createAt: Instant,
    val updateAt: Instant
)