package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class GiveLoyaltyPointRequest(
    val point: Int,
    val type: String,
    val memberId: String? = null,
    val countryCode: String? = null,
    val phoneNumber: String? = null
)