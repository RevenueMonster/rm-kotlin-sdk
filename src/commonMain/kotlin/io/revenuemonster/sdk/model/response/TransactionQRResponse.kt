package io.revenuemonster.sdk.model.response

import io.revenuemonster.sdk.model.common.Expiry
import io.revenuemonster.sdk.model.common.Store
import io.revenuemonster.sdk.model.common.TransactionQROrder
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TransactionQRResponse(
    val store: Store,
    val type: String,
    val isPreFillAmount: Boolean,
    val currencyType: String,
    val amount: Long,
    val platform: String,
    val method: List<String>,
    val expiry: Expiry?,
    val code: String,
    val status: String,
    val qrCodeUrl: String,
    val redirectUrl: String,
    val order: TransactionQROrder,
    val createdAt: Instant,
    val updatedAt: Instant
)
