package io.revenuemonster.sdk.model.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class LoyaltyMemberHistory(
    val key : String,
    val merchantKey : String,
    val type : String,
    val description : String,
    val point : Int,
    val credit : Int,
    val creditBalance : Int,
    val createdAt : Instant,
    val updatedAt : Instant
)