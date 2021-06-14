package org.rm.sdk

import org.rm.sdk.model.Items
import org.rm.sdk.model.Store

class MerchantModule(private val revenueMonsterSDK: RevenueMonsterSDK) {
    suspend fun getStores(): Items<Store> {
        return revenueMonsterSDK.call<Items<Store>>("/v3/stores")
    }
}
