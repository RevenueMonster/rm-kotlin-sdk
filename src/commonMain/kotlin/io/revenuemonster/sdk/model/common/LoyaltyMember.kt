package io.revenuemonster.sdk.model.common

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class LoyaltyMember(
    val id : String,
    val name : String,
    val email : String,
    val nric : String,
    val address: Address,
    val gender : String,
    val state : String,
    val birthDate : Instant,
    val loyaltyPoint : Int,
    val countryCode : String,
    val phoneNumber : String,
    val profileImageUrl : String,
//    val memberTier : String?,
    val hasPinCode : Boolean,
    val status : String,
    val createdAt : Instant
)