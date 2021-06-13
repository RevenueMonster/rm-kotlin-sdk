package org.rm.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class AccessToken(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: UInt,
    val refreshToken: String,
    val refreshTokenExpiresIn: UInt,
)
