package org.rm.sdk.model.response

data class OnlinePaymentResponse(
    val checkoutId: String,
    val url: String
)