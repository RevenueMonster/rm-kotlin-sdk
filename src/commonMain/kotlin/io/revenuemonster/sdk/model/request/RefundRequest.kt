package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RefundRequest(
    val transactionId : String,
    val refund : Refund,
    val reason : String
)



@Serializable
data class Refund(
    val type : String,
    val currencyType: String,
    val amount : Int
)