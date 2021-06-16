package org.rm.sdk.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

//Get Transaction QR Code/URL
//Get Transaction QR Code/URL By Code
@Serializable
data class TransactionQRURL(
    val storeId : String ?= "",
    val type : String,
    val isPreFillAmount : Boolean,
    val currencyType: String,
    val amount : UInt,
    val platform: String,
    val method: List<String>,
    val expiry : Expiry,
    val code : String,
    val status: String,
    val qrCodeUrl: String,
    val redirectUrl : String,
    val createAt: Instant?= null,
    val updateAt: Instant?= null
)

//after testing why every transaction storeid, createAt and updateAt is empty????
