package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class GetQRURLByCheckoutID(
    val checkoutId : String,
    val method : String,
    val type : String
)