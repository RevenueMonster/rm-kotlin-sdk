package io.revenuemonster.sdk

import kotlinx.datetime.Instant

class OAuthCredential(
    val accessToken: String,
    val refreshToken: String,
    val expireDateTime: Instant,
) {
}