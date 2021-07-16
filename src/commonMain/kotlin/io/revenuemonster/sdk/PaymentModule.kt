package io.revenuemonster.sdk

import io.ktor.http.*
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.request.QuickPayRequest
import io.revenuemonster.sdk.model.request.RefundRequest
import io.revenuemonster.sdk.model.request.TransactionQRRequest
import io.revenuemonster.sdk.model.response.*

class PaymentModule(private val sdk: RevenueMonsterSDK) {

    private val version = "v3"

    suspend fun getTransactionQRURL(): Items<GetTransactionQRURLResponse> {
        return sdk.call<Any, Items<GetTransactionQRURLResponse>>(url = "/$version/payment/transaction/qrcodes")
    }

    suspend fun getTransactionQRURLByCode(code: String): Item<GetTransactionQRURLResponse> {
        return sdk.call<Any, Item<GetTransactionQRURLResponse>>(url = "/$version/payment/transaction/qrcode/$code")
    }

    suspend fun getTransactionByCode(code: String): Items<GetTransactionResponse> {
        return sdk.call<Any, Items<GetTransactionResponse>>(url = "/$version/payment/transaction/qrcode/$code/transactions")
    }

    suspend fun generateTransactionQR(data: TransactionQRRequest): Item<TransactionQRResponse> {
        return sdk.call<TransactionQRRequest, Item<TransactionQRResponse>>(
            url = "/$version/payment/transaction/qrcode",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun quickPay(data: QuickPayRequest): Item<QuickPayResponse> {
        return sdk.call<QuickPayRequest, Item<QuickPayResponse>>(
            url = "/$version/payment/quickpay",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun refund(data: RefundRequest): Item<RefundResponse> {
        return sdk.call<RefundRequest, Item<RefundResponse>>(
            url = "/$version/payment/refund",
            method = HttpMethod.Post,
            body = data
        )
    }


}
