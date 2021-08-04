package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class TopUpBalanceOnlineRequest(
    val redirectUrl: String,
    val topUpAmount: Int
)