package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class TopUpBalanceOfflineRequest(
    val authCode : String,
    val storeId : String,
    val memberId : String,
    val order : TopUpBalanceOrderRequest,
)

@Serializable
data class TopUpBalanceOrderRequest(
    val id : String,
    val currencyType: String,
    val amount : Int
)