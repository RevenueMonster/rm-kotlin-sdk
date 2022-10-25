package io.revenuemonster.sdk.model.auth

import kotlinx.datetime.Instant

class OAuthCredential(
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String,
    val refreshTokenExpiresIn: Int,
    val privateKey : String,
    val sandbox: Boolean
)