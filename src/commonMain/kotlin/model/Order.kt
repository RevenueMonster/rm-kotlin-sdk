package org.rm.sdk.model

data class Order(
    val id : String,
    val title : String,
    val details : String,
    val additionalData : String,
    val currencyType: String,
    val amount : UInt
)