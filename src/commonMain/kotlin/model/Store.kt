package org.rm.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class Store(
    val id: String,
    val name: String,
    val postCode: String,
    val city: String,
    val status: String,
    val createdAt: String,
)
