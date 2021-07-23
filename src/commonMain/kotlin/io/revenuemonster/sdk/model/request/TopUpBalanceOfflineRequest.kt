package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class TopUpBalanceOfflineRequest(
    val authCode : String,
    val order : TopUpBalanceOrderRequest,
    val storeId : String
)

@Serializable
data class TopUpBalanceOrderRequest(
    val id : String,
    val currencyType: String,
    val amount : Int
)