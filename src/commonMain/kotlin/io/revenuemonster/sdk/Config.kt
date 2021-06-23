package io.revenuemonster.sdk

data class Config(
    val clientID: String,
    val clientSecret: String,
    val privateKey: String,
    val publicKey: String,
    val sandbox: Boolean,
)