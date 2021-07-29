package io.revenuemonster.sdk.model.response

import io.revenuemonster.sdk.model.common.Order
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class OnlineTransaction(
    val id : String,
    val order : Order,
    val type : String,
    val transactionId : String,
    val platform : String,
    val method : List<String>,
    val redirectUrl : String,
    val notifyUrl : String,
    val startAt : Instant,
    val endAt : Instant,
    val status : String,
    val createdAt : Instant,
    val updatedAt : Instant
)