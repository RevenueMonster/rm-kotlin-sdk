package org.rm.sdk

import kotlinx.serialization.Serializable

@Serializable
data class Items<T>(val items: List<T>, val code: String)

@Serializable
data class Store(val id: String, val name: String)

class MerchantModule(private val revenueMonsterSDK: RevenueMonsterSDK) {
    suspend fun getStores() {
//        try {
//            val response: Items<Store> = revenueMonsterSDK.client.get("https://sb-open.revenuemonster.my/v3/stores")
//            print(response)
// //            return response
//        } catch (e: Exception) {
//            print(e.message)
//            throw e
//        }
    }
}
