package io.revenuemonster.sdk.model.auth

data class Config(
    val clientID: String,
    val clientSecret: String,
    val privateKey: String,
    val sandbox: Boolean,
)