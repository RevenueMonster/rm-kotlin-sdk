package org.rm.sdk

import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import org.rm.sdk.model.*


class PaymentModule(private val sdk: RevenueMonsterSDK) {


    suspend fun getTransactionQRURL(): Items<TransactionQRURL>{
            return sdk.call("/v3/payment/transaction/qrcodes")
    }

    suspend fun getTransactionQRURLByCode(code : String): Item<TransactionQRURL> {
        return sdk.call("/v3/payment/transaction/qrcode/$code")
    }

    suspend fun getTransactionByCode(code:String): Items<TransactionByCode>{
        return sdk.call("/v3/payment/transaction/qrcode/$code/transactions")
    }


    suspend fun quickPay(data : QuickPay): Item<QuickPayTransaction>{
        return sdk.call(
            url = "/v3/payment/quickpay",
            requestMethod = HttpMethod.Post,
            requestBody = data
        )
    }





}
