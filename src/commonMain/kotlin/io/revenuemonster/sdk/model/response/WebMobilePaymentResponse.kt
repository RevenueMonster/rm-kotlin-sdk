package io.revenuemonster.sdk.model.response

import kotlinx.serialization.Serializable

@Serializable
data class WebMobilePaymentResponse(
    val checkoutId: String,
    val url: String? = null
)
