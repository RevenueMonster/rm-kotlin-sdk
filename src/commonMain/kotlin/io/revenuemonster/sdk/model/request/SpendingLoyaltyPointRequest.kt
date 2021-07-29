package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SpendingLoyaltyPointRequest(
    val currencyType: String,
    val amount: Int,
    val type: String,
    val memberId: String? = null,
    val countryCode: String? = null,
    val phoneNumber: String? = null
)