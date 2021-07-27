package io.revenuemonster.sdk.model.response

import io.revenuemonster.sdk.model.common.Expiry
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Voucher(
    val key : String,
    val label : String,
    val voucherBatchKey : String?,
    val type : String,
    val amount : Int?= null,
    val discountRate : Int,
    val minimumSpendAmount : Int?=null,
    val origin : String,
    val imageUrl : String,
    val memberProfile : String?=null,
    val assignedAt : Instant,
    val qrUrl : String,
    val code : String,
    val isShipping : Boolean,
    val address : String?=null,
    val expiry : Expiry,
    val usedAt : Instant,
    val redeemedAt : Instant,
    val isDeviceRedeem : Boolean,
    val status : String,
    val createdAt : Instant,
    val updatedAt : Instant


)