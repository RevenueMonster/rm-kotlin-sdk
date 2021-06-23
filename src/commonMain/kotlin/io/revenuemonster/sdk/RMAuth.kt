package io.revenuemonster.sdk

import kotlinx.serialization.Serializable


data class RMAuth(
    val clientID: String,
    val clientSecret: String,
    val privateKey: String,
    val publicKey: String,
    val sandbox: Boolean,
)