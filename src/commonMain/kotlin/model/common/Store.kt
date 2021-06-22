package io.revenuemonster.sdk.model.common

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Store(
    val id: String,
    val name: String,
    val addressLine1 : String,
    val addressLine2: String,
    val postCode: String,
    val city: String,
    val state: String,
    val country: String,
    val countryCode : String,
    val phoneNumber : String,
    val geoLocation : GeoLocation,
    val status: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)
