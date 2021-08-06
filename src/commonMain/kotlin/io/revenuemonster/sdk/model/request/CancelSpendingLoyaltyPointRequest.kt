package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class CancelSpendingLoyaltyPointRequest(
    val id: String
)