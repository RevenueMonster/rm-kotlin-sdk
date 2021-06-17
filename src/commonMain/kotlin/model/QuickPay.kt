package org.rm.sdk.model

import kotlinx.serialization.Serializable

//request parameters
@Serializable
data class QuickPay(
    val authCode : String,
    val order : QuickPayOrderRequest,
    val extraInfo : ExtraInfo,
    val ipAddress : String,
    val terminalId : String ?= "",
    val storeId : String
)