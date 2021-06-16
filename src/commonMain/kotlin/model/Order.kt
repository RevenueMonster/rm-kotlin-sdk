package org.rm.sdk.model

import kotlinx.serialization.Serializable

//quick pay request
@Serializable
data class Order(
    val id : String,
    val title : String,
    val details : String,
    val additionalData : String,
    val currencyType: String,
    val amount : UInt
)
