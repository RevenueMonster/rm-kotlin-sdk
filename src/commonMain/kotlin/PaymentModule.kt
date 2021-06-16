package org.rm.sdk

import io.ktor.client.request.*
import org.rm.sdk.model.Item
import org.rm.sdk.model.Items
import org.rm.sdk.model.Transaction
import org.rm.sdk.model.TransactionQRURL


class PaymentModule(private val sdk: RevenueMonsterSDK) {


    suspend fun getTransactionQRURL(): Items<TransactionQRURL>{
            return sdk.call("/v3/payment/transaction/qrcodes")
    }

    suspend fun getTransactionQRURLByCode(code : String): Item<TransactionQRURL> {
        return sdk.call("/v3/payment/transaction/qrcode/$code")
    }



}
