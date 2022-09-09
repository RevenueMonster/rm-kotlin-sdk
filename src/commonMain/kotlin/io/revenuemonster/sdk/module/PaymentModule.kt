package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.enum.PaymentMethod
import io.revenuemonster.sdk.model.request.*
import io.revenuemonster.sdk.model.response.*

class PaymentModule(private val sdk: RevenueMonsterSDK) {
    // quick pay
    suspend fun quickPay(data: QuickPayRequest): Item<Transaction> {
        return sdk.call(
            url = "/v3/payment/quickpay",
            method = HttpMethod.Post,
            body = data
        )
    }

    // Transaction  QR
    suspend fun generateTransactionQR(data: TransactionQRRequest): Item<TransactionQRURL> {
        return sdk.call(
            url = "/v3/payment/transaction/qrcode",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun getTransactionQRURL(): Items<TransactionQRURL> {
        return sdk.call<Any, Items<TransactionQRURL>>(url = "/v3/payment/transaction/qrcodes")
    }

    suspend fun getTransactionQRURLByCode(code: String): Item<TransactionQRURL> {
        return sdk.call<Any, Item<TransactionQRURL>>(url = "/v3/payment/transaction/qrcode/$code")
    }

    suspend fun getTransactionByCode(code: String): Items<GetTransactionResponse> {
        return sdk.call<Any, Items<GetTransactionResponse>>(url = "/v3/payment/transaction/qrcode/$code/transactions")
    }

    // Web Mobile Payment
    suspend fun webMobilePayment(data: WebMobilePaymentRequest): Item<WebMobilePaymentResponse> {
        return sdk.call(
            url = "/v3/payment/online",
            method = HttpMethod.Post,
            body = data
        )
    }

    // Get QR Code & URL By Checkout ID (QR Code)
    suspend fun getQRCodeByCheckoutID(checkoutId: String, method: PaymentMethod): Item<GetQRCodeByCheckoutIDResponse> {
        val data = GetQRURLByCheckoutID(checkoutId, method, "QRCODE")
        return sdk.call(
            url = "/v3/payment/online/checkout",
            method = HttpMethod.Post,
            body = data
        )
    }

    //Get QR Code & URL By Checkout ID (URL)
    suspend fun getURLByCheckoutID(checkoutId: String, method: PaymentMethod): Item<GetURLByCheckoutIDResponse> {
        val data = GetQRURLByCheckoutID(checkoutId, method, "URL")
        return sdk.call(
            url = "/v3/payment/online/checkout",
            method = HttpMethod.Post,
            body = data
        )
    }

    // Get Online Transaction By Checkout ID
    suspend fun getOnlineTransactionByCheckoutID(checkoutId: String): Item<OnlineTransaction> {
        return sdk.call<Any, Item<OnlineTransaction>>(
            url = "/v3/payment/online?checkoutId=$checkoutId"
        )
    }

    // Refund
    suspend fun refund(data: RefundRequest): Item<Transaction> {
        return sdk.call(
            url = "/v3/payment/refund",
            method = HttpMethod.Post,
            body = data
        )
    }

    // Reverse
    // FIXME : 'Store' didnt return
    suspend fun reverse(orderId: String): Item<Transaction> {
        val data = ReverseRequest(orderId = orderId)
        return sdk.call(
            url = "/v3/payment/reverse",
            method = HttpMethod.Post,
            body = data
        )
    }

    // Query Status By Order ID
    suspend fun queryStatusByOrderID(orderId: String): Item<Transaction> {
        return sdk.call<Any, Item<Transaction>>(
            url = "/v3/payment/transaction/order/$orderId"
        )
    }

    // Query Status by Transaction ID
    suspend fun queryStatusByTransactionID(transactionId: String): Item<Transaction> {
        return sdk.call<Any, Item<Transaction>>(
            url = "/v3/payment/transaction/$transactionId"
        )
    }

    // Get Fpx Bank List
    suspend fun getFpxBanks(): Item<Map<String, Banks>> {
        return sdk.call<Any, Item<Map<String, Banks>>>(url = "/v3/payment/fpx-bank")
    }

    // Daily Settlement Report
    suspend fun dailySettlementReport(data: DailySettlementReportRequest): Items<DailySettlementReportResponse> {
        return sdk.call(
            url = "/v3/payment/reconciliation",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun getTransactions(cursor: String = ""): Items<Transaction> {
        return sdk.call<Any, Items<Transaction>>(
            url = "/v3/payment/transactions?cursor=$cursor"
        )
    }

    // Get Transactions with filter by date
    suspend fun getTransactions(
        startAt: String,
        endAt: String,
        cursor: String = ""
    ): Items<Transaction> {
        return sdk.call<Any, Items<Transaction>>(
            url = "/v3/terminal/payment/transactions?filter={\"transactionAt\":  {\"\$gte\": \"$startAt\", \"\$lte\": \"$endAt\"}}&cursor=$cursor"
        )
    }

    // Get Transactions with filter by status
    suspend fun getTransactions(status: String, cursor: String = ""): Items<Transaction> {
        return sdk.call<Any, Items<Transaction>>(
            url = "/v3/terminal/payment/transactions?filter={\"status\": \"$status\"}&cursor=$cursor"
        )
    }

    // Get Transactions with filter by date & status
    suspend fun getTransactions(
        startAt: String,
        endAt: String,
        status: String,
        cursor: String = ""
    ): Items<Transaction> {
        return sdk.call<Any, Items<Transaction>>(
            url = "/v3/terminal/payment/transactions?filter={\"transactionAt\":  {\"\$gte\": \"$startAt\", \"\$lte\": \"$endAt\"}, \"status\": \"$status\"}&cursor=$cursor"
        )
    }


}