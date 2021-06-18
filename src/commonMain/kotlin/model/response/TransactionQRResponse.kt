package model.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import org.rm.sdk.model.common.Store

@Serializable
data class TransactionQRResponse(
    val store: Store,
    val type: String,
//    val isPreFillAmount: Boolean,
//    val currencyType: String,
//    val amount: Long,
//    val platform: String,
//    val method: List<String>,
//    val expiry: Expiry?,
    val code: String,
    val status: String,
    val qrCodeUrl: String,
    val redirectUrl: String,
//    val order: TransactionQROrder,
    val createdAt: Instant,
    val updatedAt: Instant
)
