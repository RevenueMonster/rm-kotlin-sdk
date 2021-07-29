package io.revenuemonster.sdk.model.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class CheckWalletHistory(
    val id : String,
    val referenceId : String,
    val walletId : String,
    val type : String,
    val referenceType : String,
    val reference : String,
    val credit : Long,
    val currentCredit : Long,
    val sequenceId : Long,
    val transactionAt : Instant
)