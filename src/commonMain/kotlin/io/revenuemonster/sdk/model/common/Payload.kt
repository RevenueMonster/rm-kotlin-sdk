package io.revenuemonster.sdk.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Payload(
    val tnc: List<String>,
    val contactInfo: ContactInfo
)

@Serializable
data class ContactInfo(
    val email: String,
    val phoneNumber: String
)