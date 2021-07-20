package io.revenuemonster.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class ItemsC<T>(
    val items: List<T>,
    val cursor: String,
)