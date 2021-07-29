package io.revenuemonster.sdk.model.response

import io.revenuemonster.sdk.model.common.Expiry
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Vouchers(
    val id : String,
    val key : String,
    val label : String,
    val type : String,
    val amount : Int?= null,
    val discountRate : Int,
    val minimumSpendAmount : Int?=null,
    val imageUrl : String,
    val quantity : Int,
    val balanceQuantity : Int,
    val usedQuantity : Int,
    val status : String,
    val expiry : Expiry,
    val origin : String,
    val isShipping : Boolean,
    val staticCode : String,
    val isDeviceRedeem : Boolean,
    val createdAt : Instant,
    val updatedAt : Instant


)