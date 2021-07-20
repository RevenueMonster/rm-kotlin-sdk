package io.revenuemonster.sdk.model.request

import io.revenuemonster.sdk.model.enum.TransactionType
import kotlinx.serialization.Serializable

@Serializable
data class DailySettlementReportRequest (
    val transactionType : String,
    val date : String,
    val method : String,
    val region : String,
    val cursor : String
    )