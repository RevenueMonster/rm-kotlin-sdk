package io.revenuemonster.sdk.model.request

import io.revenuemonster.sdk.model.enum.PaymentMethod
import kotlinx.serialization.Serializable

@Serializable
data class GetQRURLByCheckoutID(
    val checkoutId: String,
    val method: PaymentMethod,
    val type: String
)