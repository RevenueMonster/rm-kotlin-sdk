package io.revenuemonster.sdk.model.response

import io.revenuemonster.sdk.model.common.Payee
import io.revenuemonster.sdk.model.common.Store
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TopUpBalanceOfflineResponse(
    val store : Store,
    val referenceId: String,
    val transactionId: String,
    val order : TopUpBalanceOrderResponse,
//    val payee: Payee,
    val currencyType: String,
    val platform: String,
    val method: String,
    val type: String,
    val status: String,
    val region: String,
    val createdAt : Instant,
    val updatedAt : Instant
)

@Serializable
data class TopUpBalanceOrderResponse(
    val id : String,
    val title : String,
    val detail: String,
    val amount : Int
)
