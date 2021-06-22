package io.revenuemonster.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class Items<T>(
    val items: List<T>,
    val code: String,
)
