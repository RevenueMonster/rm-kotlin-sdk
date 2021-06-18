package model.transaction

import kotlinx.serialization.Serializable
import org.rm.sdk.model.common.Payee
import org.rm.sdk.model.common.Store


////For payment -> Get All Transaction
//@Serializable
//data class Transaction (
//    val store: Store,
//    val referenceId: String,
//    val transactionId: String,
//    val order: TransactionOrder,
//    val terminalId: String,
//    val payee: Payee,
//    val currencyType: String,
//    val balanceAmount: Long,
//
//    val transactionAt: String,
//    val platform: String,
//    val method: String,
//    val type: String,
//    val status: String,
//    val region: String,
//    val createdAt: String,
//    val updatedAt: String
//)
//
//
//@Serializable
//data class TransactionOrder(
//    val id: String,
//    val title : String,
//    val detail : String,
//    val amount : Int
//)
//
//@Serializable
//data class TransactionExtraInfo(
//    val id: String,
//    val title : String,
//    val detail : String,
//    val amount : Int
//)