package org.rm.sdk

import io.ktor.client.*

class RevenueMonsterSDK {
    val payment: PaymentModule = PaymentModule(this)
    val merchant: MerchantModule = MerchantModule(this)
    val client: HttpClient by lazy {
        HttpClient()
    }

    fun getString(): String {
        return "test"
    }
}
