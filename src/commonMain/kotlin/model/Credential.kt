package io.revenuemonster.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class Credential(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: UInt,
    val refreshToken: String,
    val refreshTokenExpiresIn: UInt,
)
