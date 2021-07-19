package io.revenuemonster.sdk.model.response

import io.revenuemonster.sdk.model.common.Store
import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant


@Serializable
data class RefundResponse(
    val store: Store,
    val referenceId : String,
    val transactionId : String,
    val order : TransactionOrder,
    val terminalId : String,
    val currencyType : String,
    val balanceAmount : Int,
    val platform : String,
    val method: String,
    val transactionAt : Instant,
    val type : String,
    val status : String,
    val region : String,
    val createdAt : Instant,
    val updatedAt : Instant
)
