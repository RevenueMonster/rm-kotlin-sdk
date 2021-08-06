package io.revenuemonster.sdk.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val addressLine1: String,
    val addressLine2: String,
    val postcode: String,
    val city: String,
    val state: String,
    val country: String
)