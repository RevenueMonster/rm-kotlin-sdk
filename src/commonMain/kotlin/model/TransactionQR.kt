package org.rm.sdk.model

import kotlinx.serialization.Serializable


//Transaction QR Respond
@Serializable
data class TransactionQR(
    val amount : UInt,
    val currencyType: String,
    val method : List<String>,
    val expiry : Expiry,
    val order: TransactionQROrder,
    val redirectUrl : String,
    val type : String,
    val storeId : String,
    val isPreFillAmount: Boolean
)