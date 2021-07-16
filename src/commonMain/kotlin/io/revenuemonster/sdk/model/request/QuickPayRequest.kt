package io.revenuemonster.sdk.model.request

import io.revenuemonster.sdk.model.common.ExtraInfo
import io.revenuemonster.sdk.model.common.Order
import kotlinx.serialization.Serializable

@Serializable
data class QuickPayRequest(
    val authCode: String,
    val order: Order,
    val extraInfo: ExtraInfo? = null,
    val ipAddress: String,
    val terminalId: String,
    val storeId: String
)


