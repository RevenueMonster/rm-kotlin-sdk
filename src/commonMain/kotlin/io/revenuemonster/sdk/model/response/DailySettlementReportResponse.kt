package io.revenuemonster.sdk.model.response

import io.revenuemonster.sdk.model.common.Order
import io.revenuemonster.sdk.model.common.Store
import kotlinx.datetime.Instant

data class DailySettlementReportResponse(
    val transactionAt : Instant,
    val merchantId : String,
    val merchantName : String,
    val storeId : String,
    val storeName : String,
    val region : String,
    val method : String,
    val transactionType : String,
    val type : String,
    val transactionId : String,
    val orderId : String,
    val currencyType : String,
    val grossAmount : Double,
    val mdr : Double,
    val serviceFee : Double,
    val settlementAmount : Double,
    val store : Store,
    val referenceId : String,
    val order : Order,
    val platform : String,
    val status : String,
    val createdAt : Instant,
    val updatedAt : Instant

)