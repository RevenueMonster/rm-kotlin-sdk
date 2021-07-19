package io.revenuemonster.sdk

import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.common.Store
import io.revenuemonster.sdk.model.response.MerchantSubscriptions

class MerchantModule(private val sdk: RevenueMonsterSDK) {

    private val version = "v3"

    suspend fun getSubscriptions(): Items<MerchantSubscriptions> {
        return sdk.call<Any,Items<MerchantSubscriptions>>(
            url = "/$version/merchant/subscriptions"
        )
    }

//    suspend fun getStores(): Items<Store> {
//        return sdk.call<Any, Items<Store>>("/v3/stores")
//    }
}
