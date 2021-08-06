package io.revenuemonster.sdk.model.common

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class MemberProfile(
    val id: String,
    val key: String,
    val name: String,
    val email: String,
    val nric: String,
    val birthDate: String,
    val gender: String,
    val address: Address,
    val memberTier: String?,
    val totalLoyaltyPoint: Int,
    val hasPinCode: Boolean,
    val loyaltyPointBalance: Int,
    val spendingPoint: Int,
    val creditBalance: Int,
    val status: String,
    val createdAt: Instant,
    val updatedAt: Instant
)