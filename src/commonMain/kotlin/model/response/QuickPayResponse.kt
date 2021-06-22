package model.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import io.revenuemonster.sdk.model.common.Payee
import io.revenuemonster.sdk.model.common.Store


@Serializable
data class QuickPayResponse(
    val store: Store,
    val referenceId: String,
    val transactionId: String,
    val order: QuickPayResponseOrder,
    val terminalId: String,
    val payee: Payee?=null,
    val currencyType: String,
    val balanceAmount: Long,
    val platform: String,
    val method: String,
    val transactionAt: String,
    val type: String,
    val status: String,
    val region: String,
    val createdAt: Instant,
    val updatedAt: Instant
)


@Serializable
data class QuickPayResponseOrder(
    val id: String,
    val title: String,
    val detail: String,
    val amount: Int
)

