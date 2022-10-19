package io.revenuemonster.sdk.model.response

import io.revenuemonster.sdk.model.common.ErrorMsg
import io.revenuemonster.sdk.model.common.OrderResponse
import io.revenuemonster.sdk.model.common.Store
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val store: Store,
    val referenceId: String,
    val transactionId: String,
    val order: OrderResponse,
    val terminalId: String? = null,
    val payee: Payee? = null,
    val currencyType: String,
    val balanceAmount: Int,
    val finalAmount: Int = 0,
    val voucher: TransactionVoucher? = null,
    val platform: String,
    val method: String,
    val transactionAt: Instant,
    val type: String,
    val status: String,
    val error: ErrorMsg? = null,
    val region: String,
    val extraInfo: ExtraInfo? = null,
    val createdAt: Instant,
    val updatedAt: Instant
)

@Serializable
data class ExtraInfo(
    val card: CardBean? = null,
    val extraFee: List<ExtraFee>? = null,
    val onlineBanking: OnlineBankingBean? = null
)

@Serializable
data class CardBean (
    val cardType: CardType? = null,
    val provider: String? = null,
    val isTokenization: Boolean? = null,
    val token: String? = null,
    val maskNo: String? = null,
    val inputType: String? = null,
    val referenceId: String? = null,
    val domain: String? = null,
    val secondaryReferenceId: String? = null
)

@Serializable
data class CardType (
    val brand: String? = null,
    val type: String? = null,
    val issuer: String? = null,
    val alpha2: String? = null,
    val alpha3: String? = null,
    val country: String? = null
)

@Serializable
data class TransactionVoucher(
    val key: String? = null,
    val discountAmount: Int? = null
)

@Serializable
data class ExtraFee(
    val type: String? = null,
    val referenceId: String? = null,
    val amount: Int? = null,
    val feeType: String? = null,
    val feeValue: Int? = null,
    val isIncludedMDR: Boolean? = null,
    val isRefundAllowed: Boolean? = null
)

@Serializable
data class Payee(
    val userId: String? = null
)

@Serializable
data class OnlineBankingBean(
    val buyerName: String? = null,
    val bankId: String? = null,
)
