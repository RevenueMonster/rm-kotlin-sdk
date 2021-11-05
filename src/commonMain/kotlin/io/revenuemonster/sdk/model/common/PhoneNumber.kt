package io.revenuemonster.sdk.model.common

import kotlinx.serialization.Serializable

@Serializable
data class PhoneNumber(
    val countryCode: String,
    val phoneNumber: String,
)