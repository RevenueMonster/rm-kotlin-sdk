package io.revenuemonster.sdk.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Banks(
    val code: String,
    val isOnline: Boolean,
    val name: String
)