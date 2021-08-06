package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class CheckMemberRequest(
    val countryCode: String,
    val phoneNumber: String,
)