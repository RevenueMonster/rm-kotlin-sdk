package org.rm.sdk.model

import kotlinx.datetime.Instant

//transaction QR response (transaction)
data class TransactionQRRespond(
    val store: Store,
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
    val order: TransactionQROrder,
    val createAt: Instant,
    val updateAt: Instant
)