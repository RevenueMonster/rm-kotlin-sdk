package io.revenuemonster.sdk.model.response

import kotlinx.serialization.Serializable

@Serializable
data class GetURLByCheckoutIDResponse (
    val type: String,
    val url: String
)