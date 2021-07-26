package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.request.*
import io.revenuemonster.sdk.model.response.*

class PaymentModule(private val sdk: RevenueMonsterSDK) {

    suspend fun getTransactionQRURL(): Items<GetTransactionQRURLResponse> {
        return sdk.call<Any, Items<GetTransactionQRURLResponse>>(url = "/payment/transaction/qrcodes")
    }

    suspend fun getTransactionQRURLByCode(code: String): Item<GetTransactionQRURLResponse> {
        return sdk.call<Any, Item<GetTransactionQRURLResponse>>(url = "/payment/transaction/qrcode/$code")
    }

    suspend fun getTransactionByCode(code: String): Items<GetTransactionResponse> {
        return sdk.call<Any, Items<GetTransactionResponse>>(url = "/payment/transaction/qrcode/$code/transactions")
    }

    suspend fun generateTransactionQR(data: TransactionQRRequest): Item<TransactionQRResponse> {
        return sdk.call<TransactionQRRequest, Item<TransactionQRResponse>>(
            url = "/payment/transaction/qrcode",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun quickPay(data: QuickPayRequest): Item<QuickPayResponse> {
        return sdk.call<QuickPayRequest, Item<QuickPayResponse>>(
            url = "/payment/quickpay",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun refund(data: RefundRequest): Item<RefundResponse> {
        return sdk.call<RefundRequest, Item<RefundResponse>>(
            url = "/payment/refund",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun getFpxBanks() : Item<Map<String,Banks>>{
        return sdk.call<Any,Item<Map<String,Banks>>>(url = "/payment/fpx-bank")
    }

    suspend fun reverse(data : ReverseRequest) : Item<GetTransactionResponse>{
        return sdk.call<ReverseRequest,Item<GetTransactionResponse>>(
            url = "/payment/reverse",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun queryStatusByOrderID(orderId : String)  : Item<Transaction>{
        return sdk.call<Any,  Item<Transaction>>(
            url = "/payment/transaction/order/$orderId"
        )
    }

    suspend fun queryStatusByTransactionID(transactionId : String)  : Item<Transaction>{
        return sdk.call<Any,  Item<Transaction>>(
            url = "/payment/transaction/$transactionId"
        )
    }

    suspend fun getAllTransactions() : Items<Transaction> {
        return sdk.call<Any, Items<Transaction>>(
            url = "/payment/transactions"
        )
    }

    suspend fun webMobilePayment(data : WebMobilePaymentRequest) : Item<WebMobilePaymentResponse>{
        return sdk.call<WebMobilePaymentRequest,Item<WebMobilePaymentResponse>>(
            url = "/payment/online",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun getQRCodeByCheckoutID(data: GetQRCodeByCheckoutIDRequest) : Item<GetQRCodeByCheckoutIDResponse>{
        return sdk.call<GetQRCodeByCheckoutIDRequest,Item<GetQRCodeByCheckoutIDResponse>>(
            url = "/online/checkout",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun getURLByCheckoutID(data: GetURLByCheckoutIDRequest) : Item<GetURLByCheckoutIDResponse>{
        return sdk.call<GetURLByCheckoutIDRequest, Item<GetURLByCheckoutIDResponse>>(
            url = "/online/checkout",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun dailySettlementReport(data : DailySettlementReportRequest): Items<DailySettlementReportResponse>{
        return sdk.call<DailySettlementReportRequest, Items<DailySettlementReportResponse>>(
            url = "/payment/reconciliation",
            method = HttpMethod.Post,
            body = data
        )
    }



}
