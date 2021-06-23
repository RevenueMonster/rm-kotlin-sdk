package io.revenuemonster.sdk

import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.common.Store

class MerchantModule(private val sdk: RevenueMonsterSDK) {

    suspend fun getStores(): Items<Store> {
        sdk.getAccessToken()
        return sdk.call<Any, Items<Store>>("/v3/stores")
    }
}
