package io.revenuemonster.sdk

import io.ktor.http.*
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.request.QuickPayRequest
import io.revenuemonster.sdk.model.request.TransactionQRRequest
import io.revenuemonster.sdk.model.response.GetTransactionQRURLResponse
import io.revenuemonster.sdk.model.response.GetTransactionResponse
import io.revenuemonster.sdk.model.response.QuickPayResponse
import io.revenuemonster.sdk.model.response.TransactionQRResponse

class PaymentModule(private val sdk: RevenueMonsterSDK) {

    suspend fun getTransactionQRURL(): Items<GetTransactionQRURLResponse> {
        return sdk.call<Any, Items<GetTransactionQRURLResponse>>(url = "/v3/payment/transaction/qrcodes")
    }

    suspend fun getTransactionQRURLByCode(code: String): Item<GetTransactionQRURLResponse> {
        return sdk.call<Any, Item<GetTransactionQRURLResponse>>(url = "/v3/payment/transaction/qrcode/$code")
    }

    suspend fun getTransactionByCode(code: String): Items<GetTransactionResponse> {
        return sdk.call<Any, Items<GetTransactionResponse>>(url = "/v3/payment/transaction/qrcode/$code/transactions")
    }

    suspend fun generateTransactionQR(data: TransactionQRRequest): Item<TransactionQRResponse> {
        return sdk.call<TransactionQRRequest, Item<TransactionQRResponse>>(
            url = "/v3/payment/transaction/qrcode",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun quickPay(data: QuickPayRequest): Item<QuickPayResponse> {
        return sdk.call<QuickPayRequest, Item<QuickPayResponse>>(
            url = "/v3/payment/quickpay",
            method = HttpMethod.Post,
            body = data
        )
    }
}
