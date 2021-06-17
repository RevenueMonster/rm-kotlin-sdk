package org.rm.sdk.model

import kotlinx.serialization.Serializable

//quick pay response
@Serializable
data class QuickPayOrderRequest(
    val id: String,
    val title: String,
    val detail: String,
    val additionalData : String,
    val currencyType : String,
    val amount: UInt
)