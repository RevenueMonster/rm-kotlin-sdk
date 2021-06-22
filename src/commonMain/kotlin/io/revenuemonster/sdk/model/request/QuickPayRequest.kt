package com.github.revenuemonster.model.request

import io.revenuemonster.sdk.model.common.ExtraInfo
import kotlinx.serialization.Serializable


@Serializable
data class QuickPayRequest(
    val authCode: String,
    val order: QuickPayOrder,
    val extraInfo: ExtraInfo? = null,
    val ipAddress: String,
    val terminalId: String,
    val storeId: String
)


@Serializable
data class QuickPayOrder(
    val id: String,
    val title: String,
    val detail: String,
    val amount: Int,
    val currencyType: String,
    val additionalData: String,
)




