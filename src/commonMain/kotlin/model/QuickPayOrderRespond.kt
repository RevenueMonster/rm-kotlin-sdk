package org.rm.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class QuickPayOrderRespond(
    val id: String,
    val title: String,
    val detail: String,
    val amount: UInt
)