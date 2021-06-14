package org.rm.sdk

class PaymentModule(private val revenueMonsterSDK: RevenueMonsterSDK) {
    suspend fun find(id: String) {
//        try {
//            val item: Item<Payment> =
//                .client.get("https://sb-open.revenuemonster.my/v3/payment/transaction/$id")
//            println("debug !!!!!!!!!!!!!!!!!!!")
//            println(item.item.status)
// //            return response
//        } catch (e: Exception) {
//            println("C")
//            print(e.message)
//            throw e
//        }
    }

    suspend fun qrPay() {
//        println("A")
//        try {
//            println("B")
//            println(revenueMonsterSDK.client)
//            val response: HttpResponse = revenueMonsterSDK.client.get("https://ktor.io/")
//            println("XXX")
//            println("debug !!!!!!!!!!!!!!!!!!!")
//            println(response.status)
//            println(String(response.content.readRemaining().readBytes()))
// //            return response
//        } catch (e: Exception) {
//            println("C")
//            print(e.message)
//            throw e
//        }
    }
}
