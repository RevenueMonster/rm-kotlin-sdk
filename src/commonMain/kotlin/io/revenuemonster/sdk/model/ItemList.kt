package io.revenuemonster.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class ItemList<T>(
    val item: List<T>,
    val code: String,
)