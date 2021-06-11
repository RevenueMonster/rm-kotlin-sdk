package org.rm.sdk

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

class RevenueMonsterSDK {
    val payment: PaymentModule = PaymentModule(this)
    val merchant: MerchantModule = MerchantModule(this)
    val client: HttpClient by lazy {
        HttpClient() {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    private fun call(url: String): String {
        return url
    }

    fun getString(): String {
        return "test1111"
    }
}
