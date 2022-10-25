package io.revenuemonster.sdk.model.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val id: String,
    val firstName: String,
    val lastName: String,
    val countryCode: String,
    val phoneNumber: String,
    val email: String,
    val avatarUrl: String,
    val status: String,
    val storeId: List<String>,
    val isActive: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant
)