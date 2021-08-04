package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ReverseRequest(
    val orderId: String
)