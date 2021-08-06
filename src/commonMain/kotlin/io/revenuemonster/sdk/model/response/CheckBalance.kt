package io.revenuemonster.sdk.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CheckBalance(
    val id: String,
    val referenceId: String,
    val credit: Long
)