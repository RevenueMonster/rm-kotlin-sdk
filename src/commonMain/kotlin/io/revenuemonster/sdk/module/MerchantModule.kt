package io.revenuemonster.sdk.module

import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.response.MerchantProfile
import io.revenuemonster.sdk.model.response.MerchantSubscriptions

class MerchantModule(private val sdk: RevenueMonsterSDK) {

    suspend fun getSubscriptions(): Item<List<MerchantSubscriptions>> {
        return sdk.call<Any, Item<List<MerchantSubscriptions>>>(
            url = "/merchant/subscriptions"
        )
    }

    suspend fun getProfile(): Item<MerchantProfile> {
        return sdk.call<Any, Item<MerchantProfile>>(
            url = "/merchant"
        )
    }

}
