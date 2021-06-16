package org.rm.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class TransactionQROrder(
    val details: String,
    val title: String,
    val additionalData: String
)