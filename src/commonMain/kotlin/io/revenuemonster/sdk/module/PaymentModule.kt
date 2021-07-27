package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.request.*
import io.revenuemonster.sdk.model.response.*

class PaymentModule(private val sdk: RevenueMonsterSDK) {
    //quick pay
    suspend fun quickPay(data: QuickPayRequest): Item<QuickPayResponse> {
        return sdk.call<QuickPayRequest, Item<QuickPayResponse>>(
            url = "/payment/quickpay",
            method = HttpMethod.Post,
            body = data
        )
    }

    //Transaction  QR
    suspend fun generateTransactionQR(data: TransactionQRRequest): Item<TransactionQRResponse> {
        return sdk.call<TransactionQRRequest, Item<TransactionQRResponse>>(
            url = "/payment/transaction/qrcode",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun getTransactionQRURL(): Items<GetTransactionQRURLResponse> {
        return sdk.call<Any, Items<GetTransactionQRURLResponse>>(url = "/payment/transaction/qrcodes")
    }

    suspend fun getTransactionQRURLByCode(code: String): Item<GetTransactionQRURLResponse> {
        return sdk.call<Any, Item<GetTransactionQRURLResponse>>(url = "/payment/transaction/qrcode/$code")
    }

    suspend fun getTransactionByCode(code: String): Items<GetTransactionResponse> {
        return sdk.call<Any, Items<GetTransactionResponse>>(url = "/payment/transaction/qrcode/$code/transactions")
    }

    //Web Mobile Payment
    suspend fun webMobilePayment(data : WebMobilePaymentRequest) : Item<WebMobilePaymentResponse>{
        return sdk.call<WebMobilePaymentRequest,Item<WebMobilePaymentResponse>>(
            url = "/payment/online",
            method = HttpMethod.Post,
            body = data
        )
    }

    //Get QR Code & URL By Checkout ID (QR Code)
    suspend fun getQRCodeByCheckoutID(checkoutId: String, method: String): Item<GetQRCodeByCheckoutIDResponse> {
        val data = GetQRURLByCheckoutID(checkoutId, method, "QRCODE")
        return sdk.call<GetQRURLByCheckoutID, Item<GetQRCodeByCheckoutIDResponse>>(
            url = "/payment/online/checkout",
            method = HttpMethod.Post,
            body = data
        )
    }

    //Get QR Code & URL By Checkout ID (URL)
    suspend fun getURLByCheckoutID(checkoutId: String, method: String): Item<GetURLByCheckoutIDResponse> {
        val data = GetQRURLByCheckoutID(checkoutId, method, "URL")
        return sdk.call<GetQRURLByCheckoutID, Item<GetURLByCheckoutIDResponse>>(
            url = "/payment/online/checkout",
            method = HttpMethod.Post,
            body = data
        )
    }

    //Get Online Transaction By Checkout ID
    suspend fun getOnlineTransactionByCheckoutID(checkoutId : String) : Item<OnlineTransaction>{
        return sdk.call<Any,Item<OnlineTransaction>>(
            url = "/payment/online?checkoutId=$checkoutId"
        )
    }

    //Refund
    suspend fun refund(data: RefundRequest): Item<RefundResponse> {
        return sdk.call<RefundRequest, Item<RefundResponse>>(
            url = "/payment/refund",
            method = HttpMethod.Post,
            body = data
        )
    }

    //Reverse
    suspend fun reverse(data : ReverseRequest) : Item<GetTransactionResponse>{
        return sdk.call<ReverseRequest,Item<GetTransactionResponse>>(
            url = "/payment/reverse",
            method = HttpMethod.Post,
            body = data
        )
    }

    //Query Status By Order ID
    suspend fun queryStatusByOrderID(orderId : String)  : Item<Transaction>{
        return sdk.call<Any,  Item<Transaction>>(
            url = "/payment/transaction/order/$orderId"
        )
    }

    //Query Status by Transaction ID
    suspend fun queryStatusByTransactionID(transactionId : String)  : Item<Transaction>{
        return sdk.call<Any,  Item<Transaction>>(
            url = "/payment/transaction/$transactionId"
        )
    }

    //Get Fpx Bank List
    suspend fun getFpxBanks() : Item<Map<String,Banks>>{
        return sdk.call<Any,Item<Map<String,Banks>>>(url = "/payment/fpx-bank")
    }

    //Get All Transactions
    suspend fun getAllTransactions() : Items<Transaction> {
        return sdk.call<Any, Items<Transaction>>(
            url = "/payment/transactions"
        )
    }

    //Daily Settlement Report
    suspend fun dailySettlementReport(data : DailySettlementReportRequest): Items<DailySettlementReportResponse>{
        return sdk.call<DailySettlementReportRequest, Items<DailySettlementReportResponse>>(
            url = "/payment/reconciliation",
            method = HttpMethod.Post,
            body = data
        )
    }


}
