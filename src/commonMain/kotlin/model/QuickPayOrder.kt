package org.rm.sdk.model

import kotlinx.serialization.Serializable

//quick pay response
@Serializable
data class QuickPayOrder(
    val id: String,
    val title: String,
    val detail: String,
    val amount: UInt
)