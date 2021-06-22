
package io.revenuemonster.sdk.model.common

import kotlinx.serialization.Serializable

@Serializable
data class GeoLocation(
    val latitude: Float,
    val longitude: Float
)