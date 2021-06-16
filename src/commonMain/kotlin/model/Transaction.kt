package org.rm.sdk.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

//quick pay
@Serializable
data class Transaction(
    val store : Store,
    val referenceId : String,
    val transactionId : String,
    val order: Order,
    val payee : Payee,
    val currencyType : String,
    val platform : String,
    val method : List<String>,
    val type : String,
    val status : String,
    val region : String,
    val error : String,
    val createAt : Instant,
    val updateAt : Instant
)











