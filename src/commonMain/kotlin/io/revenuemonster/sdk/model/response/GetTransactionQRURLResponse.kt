package io.revenuemonster.sdk.model.response

import io.revenuemonster.sdk.model.common.Expiry
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class GetTransactionQRURLResponse(
    val storeId: String? = "",
    val type: String,
    val isPreFillAmount: Boolean,
    val currencyType: String,
    val amount: UInt,
    val platform: String,
    val method: List<String>,
    val expiry: Expiry,
    val code: String,
    val status: String,
    val qrCodeUrl: String,
    val redirectUrl: String,
    val createAt: Instant? = null,
    val updateAt: Instant? = null
)
