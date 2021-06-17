package org.rm.sdk

import org.rm.sdk.model.Items
import org.rm.sdk.model.common.Store

class MerchantModule(private val sdk: RevenueMonsterSDK) {
    suspend fun getStores(): Items<Store> {
        return sdk.call<Items<Store>>("/v3/stores")
    }
}
