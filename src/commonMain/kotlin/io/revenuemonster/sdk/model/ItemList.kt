package io.revenuemonster.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class ItemList<T>(
    val items: List<T>,
    val cursor: String,
)