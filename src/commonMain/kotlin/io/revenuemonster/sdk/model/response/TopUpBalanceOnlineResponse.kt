package io.revenuemonster.sdk.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TopUpBalanceOnlineResponse(
    val paymentUrl: String,
)