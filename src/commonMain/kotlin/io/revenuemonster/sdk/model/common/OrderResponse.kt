package io.revenuemonster.sdk.model.common

import kotlinx.serialization.Serializable

@Serializable
data class OrderResponse(
    val id: String,
    val title: String,
    val detail: String,
    val amount: Int,
    val additionalData: String?=null,
)