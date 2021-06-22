package io.revenuemonster.sdk.model.response

import kotlinx.serialization.Serializable

@Serializable
data class OnlinePaymentResponse(
    val checkoutId: String,
    val url: String
)
