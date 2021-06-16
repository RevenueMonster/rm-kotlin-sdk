package org.rm.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class GeoLocation(
    val latitude : Float,
    val longitude : Float
)