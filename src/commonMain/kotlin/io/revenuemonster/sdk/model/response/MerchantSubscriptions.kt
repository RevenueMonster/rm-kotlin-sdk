package io.revenuemonster.sdk.model.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class MerchantSubscriptions(
    val id : Int,
    val gracePeriod : Int,
    val expiryAt : Instant,
    val terminateAt: Instant,
    val status : String
)