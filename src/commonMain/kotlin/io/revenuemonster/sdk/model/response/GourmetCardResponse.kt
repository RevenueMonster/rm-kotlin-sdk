package io.revenuemonster.sdk.model.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class GourmetCardResponse(
    val id : String,
    val transactionId : String,
    val cardId : String,
    val memberId : String,
    val expiredAt : Instant,
    val createdAt : Instant,
    val updateAt : Instant,
    val card : GourmetCard
)


@Serializable
data class GourmetCard(
    val id: String,
    val name : String,
    val lifetime : Int,
    val lifetimeType : String,
    val amount : Int,
    val currency : String,
    val discountType : String,
    val discountValue : String,
    val minimumSpentAmount : Int,
    val wallet : String,
    val templateId : String,
    val status : String,
    val image : String,
    val icon : String,
    val createAt : Instant,
    val updateAt : Instant
)
