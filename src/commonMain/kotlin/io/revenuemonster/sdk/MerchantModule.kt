package io.revenuemonster.sdk

import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.common.Store
import io.revenuemonster.sdk.model.response.MerchantProfile
import io.revenuemonster.sdk.model.response.MerchantSubscriptions

class MerchantModule(private val sdk: RevenueMonsterSDK) {

    private val version = "v3"

    //having problem -> item : List<T>
    suspend fun getSubscriptions(): Item<MerchantSubscriptions> {
        return sdk.call<Any,Item<MerchantSubscriptions>>(
            url = "/$version/merchant/subscriptions"
        )
    }

    suspend fun getProfile() : Item<MerchantProfile>{
        return sdk.call<Any,Item<MerchantProfile>>(
            url = "/$version/merchant"
        )
    }


}
