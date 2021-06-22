package io.revenuemonster.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class Item<T>(
    val item: T,
    val code: String,
)
