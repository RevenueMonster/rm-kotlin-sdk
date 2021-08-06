package io.revenuemonster.sdk.model.request

import io.revenuemonster.sdk.model.common.Expiry
import io.revenuemonster.sdk.model.common.TransactionQROrder
import io.revenuemonster.sdk.model.enum.TransactionQRType
import kotlinx.serialization.Serializable

@Serializable
data class TransactionQRRequest(
    val amount: Int?=null,
    val currencyType: String,
    val method: List<String>?=null,
    val expiry: Expiry,
    val order: TransactionQROrder,
    val redirectUrl: String,
    val type: TransactionQRType,
    val storeId: String,
    val isPreFillAmount: Boolean
)
