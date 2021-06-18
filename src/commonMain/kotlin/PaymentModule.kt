package org.rm.sdk

import com.github.revenuemonster.model.request.QuickPayRequest
import io.ktor.http.*
import model.response.QuickPayResponse
import model.response.TransactionQRResponse
import org.rm.sdk.model.Item
import org.rm.sdk.model.Items
import org.rm.sdk.model.request.TransactionQRRequest
import org.rm.sdk.model.respone.GetTransactionQRURLResponse
import org.rm.sdk.model.respone.GetTransactionResponse

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

    suspend fun quickPay(data: QuickPayRequest): Item<QuickPayResponse> {
        return sdk.call<QuickPayRequest, Item<QuickPayResponse>>(
            url = "/v3/payment/quickpay",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun generateTransactionQR(data: TransactionQRRequest): Item<TransactionQRResponse> {
        return sdk.call<TransactionQRRequest, Item<TransactionQRResponse>>(
            url = "/v3/payment/transaction/qrcode",
            method = HttpMethod.Post,
            body = data
        )
    }

//    suspend fun quickPay(data : QuickpayRequest): Item<QuickpayResult>{
//        return sdk.call(
//            url = "/v3/payment/quickpay",
//            requestMethod = HttpMethod.Post,
//            body = data
//        )
//    }
//
//
//    suspend fun transactionQR(data : CreateStaticTransactionQRRequest) : Item<GenerateTransactionQRResult>{
//        return sdk.call(
//            url = "/v3/payment/transaction/qrcode",
//            requestMethod = HttpMethod.Post,
//            body = data
//        )
//
//    }
}
