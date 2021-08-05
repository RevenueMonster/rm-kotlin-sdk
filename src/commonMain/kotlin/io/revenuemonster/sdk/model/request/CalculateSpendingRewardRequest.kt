package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class CalculateSpendingRewardRequest(
    val currencyType: String,
    val amount: Int
)