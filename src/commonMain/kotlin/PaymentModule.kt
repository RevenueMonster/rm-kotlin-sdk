package org.rm.sdk

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.utils.io.*
import io.ktor.utils.io.core.*
import org.rm.sdk.model.Item
import org.rm.sdk.model.Payment

class PaymentModule(private val revenueMonsterSDK: RevenueMonsterSDK) {
    suspend fun find(id: String) {
        try {
            val item: Item<Payment> =
                revenueMonsterSDK.client.get("https://sb-open.revenuemonster.my/v3/payment/transaction/$id")
            println("debug !!!!!!!!!!!!!!!!!!!")
            println(item.item.status)
//            return response
        } catch (e: Exception) {
            println("C")
            print(e.message)
            throw e
        }
    }

    suspend fun qrPay() {
        println("A")
        try {
            println("B")
            println(revenueMonsterSDK.client)
            val response: HttpResponse = revenueMonsterSDK.client.get("https://ktor.io/")
            println("XXX")
            println("debug !!!!!!!!!!!!!!!!!!!")
            println(response.status)
            println(String(response.content.readRemaining().readBytes()))
//            return response
        } catch (e: Exception) {
            println("C")
            print(e.message)
            throw e
        }
    }
}
