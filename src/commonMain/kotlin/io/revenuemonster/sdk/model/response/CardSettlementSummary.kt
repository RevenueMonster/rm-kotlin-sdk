package io.revenuemonster.sdk.model.response

import kotlinx.serialization.Serializable


@Serializable
data class CardSettlementSummary(
    val summary: Summary,
    val transactions: List<TransactionSummary>
)

@Serializable
data class Summary(
    val id: String,
    val batchNo: String,
    val noOfTransactions: Int,
    val currencyType: String,
    val totalSalesAmount: Long,
    val settlementAt: String
)

@Serializable
data class TransactionSummary(
    val type: String,
    val transactionId: String,
    val currencyType: String,
    val amount: Long,
    val transactionAt: String,
)