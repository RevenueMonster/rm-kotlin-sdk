package io.revenuemonster.sdk.model.request

import io.revenuemonster.sdk.model.common.GeoLocation
import kotlinx.serialization.Serializable

@Serializable
data class StoreDetails(
    val name :String,
    val addressLine1 : String,
    val addressLine2 : String,
    val postCode : String,
    val city : String,
    val state : String,
    val country : String,
    val countryCode : String,
    val phoneNumber : String,
    val geoLocation : GeoLocation
)