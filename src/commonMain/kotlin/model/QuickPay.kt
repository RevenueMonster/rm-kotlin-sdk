package org.rm.sdk.model

data class QuickPay(
    val authCode : String,
    val order : Order,
    val extraInfo : ExtraInfo,
    val ipAddress : String,
    val terminalId : String,
    val storeId : String
)