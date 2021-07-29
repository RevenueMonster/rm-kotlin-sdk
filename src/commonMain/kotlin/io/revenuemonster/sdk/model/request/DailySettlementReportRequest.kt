package io.revenuemonster.sdk.model.request

import io.revenuemonster.sdk.model.enum.TransactionType
import kotlinx.serialization.Serializable

@Serializable
data class DailySettlementReportRequest(
    val transactionType: String,
    val date: String,
    val method: List<String>,
    val region: List<String>,
    val cursor: String
)