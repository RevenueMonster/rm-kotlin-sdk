package org.rm.sdk.model.request

import kotlinx.serialization.Serializable

import org.rm.sdk.model.common.Expiry
import org.rm.sdk.model.common.TransactionQROrder
import org.rm.sdk.model.enum.TransactionQRType

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
