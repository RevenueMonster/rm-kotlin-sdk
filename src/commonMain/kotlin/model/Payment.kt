package io.revenuemonster.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class Payment(
    val referenceId: String,
    val transactionId: String,
    val status: String,
)
