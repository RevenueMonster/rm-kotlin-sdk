package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

import io.revenuemonster.sdk.model.common.Expiry
import io.revenuemonster.sdk.model.common.TransactionQROrder
import io.revenuemonster.sdk.model.enum.TransactionQRType

@Serializable
data class TransactionQRRequest(
    val amount: Int,
    val currencyType: String,
    val method: List<String>,
    val expiry: Expiry,
    val order: TransactionQROrder,
    val redirectUrl: String,
    val type: TransactionQRType,
    val storeId: String,
    val isPreFillAmount: Boolean
)
