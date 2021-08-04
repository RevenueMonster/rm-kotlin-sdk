package io.revenuemonster.sdk.model.request

import io.revenuemonster.sdk.model.common.Address
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class RegisterMemberRequest(
    val name: String,
    val countryCode: String,
    val phoneNumber: String,
    val email: String,
    val nric: String,
    val birthDate: Instant,
    val gender: String,
    val point: Int,
    val address: Address
)