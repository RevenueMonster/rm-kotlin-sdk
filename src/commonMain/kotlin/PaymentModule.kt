package org.rm.sdk

import io.ktor.client.request.*
import io.ktor.client.statement.*

class PaymentModule(private val revenueMonsterSDK: RevenueMonsterSDK) {
    suspend fun qrPay() {
        try {
            val response: HttpResponse = revenueMonsterSDK.client.get("https://ktor.io/")
            print(response)
        } catch (e: Exception) {
            print(e.message)
        }
    }
}
