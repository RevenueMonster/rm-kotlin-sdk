package com.github.revenuemonster.model.request

import kotlinx.serialization.Serializable
import org.rm.sdk.model.common.ExtraInfo


@Serializable
data class QuickPayRequest(
    val authCode: String,
    val order: QuickPayRequestOrder,
    val extraInfo: ExtraInfo,
    val ipAddress: String,
    val terminalId : String,
    val storeId: String
)


@Serializable
data class QuickPayRequestOrder(
    val id: String,
    val title: String,
    val detail: String,
    val amount: Int,
    val currencyType: String = "MYR",
    val additionalData: String,
)




