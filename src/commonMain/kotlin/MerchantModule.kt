package org.rm.sdk

import org.rm.sdk.model.Items
import org.rm.sdk.model.Store

class MerchantModule(private val sdk: RevenueMonsterSDK) {
    suspend fun getStores(): Items<Store> {
        return sdk.call<Any, Items<Store>>("/v3/stores")
    }
}
