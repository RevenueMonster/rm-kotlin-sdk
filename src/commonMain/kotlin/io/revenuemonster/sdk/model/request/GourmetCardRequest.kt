package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class GourmetCardRequest(
    val memberCardId: String,
    val storeId: String
)