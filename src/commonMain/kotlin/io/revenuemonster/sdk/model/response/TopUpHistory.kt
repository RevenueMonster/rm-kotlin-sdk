package io.revenuemonster.sdk.model.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TopUpHistory(
    val id : String,
    val walletId : String,
    val referenceId : String,
    val adminId : String,
    val detail : String,
    val method : String,
    val status : String,
    val credit : String,
    val createdAt : Instant
)