package org.rm.sdk.model.request

import kotlinx.serialization.Serializable
import org.rm.sdk.model.common.Expiry
import org.rm.sdk.model.common.TransactionQROrder

@Serializable
data class TransactionQRRequest(
    val amount: Int,
    val currencyType: String = "MYR",
    val method: List<String> = listOf(),
    val expiry: Expiry? = null,
    val order: TransactionQROrder,
    val redirectUrl: String,
    val type:TransactionQRType = TransactionQRType.STATIC,
    val storeId: String,
    val isPreFillAmount: Boolean
)


