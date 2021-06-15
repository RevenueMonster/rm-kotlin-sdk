package org.rm.sdk.model

data class Transaction(
    val store : Store,
    val referenceId : String,
    val transactionId : String,
    val order: Order,
)