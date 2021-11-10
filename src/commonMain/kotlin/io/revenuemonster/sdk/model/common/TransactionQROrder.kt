package io.revenuemonster.sdk.model.common

import kotlinx.serialization.Serializable

@Serializable
data class TransactionQROrder(
    val detail: String,
    val title: String,
    val additionalData: String?=null
)