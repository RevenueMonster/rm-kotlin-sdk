package io.revenuemonster.sdk.module

import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.response.MerchantProfile
import io.revenuemonster.sdk.model.response.MerchantSubscriptions

class MerchantModule(private val sdk: RevenueMonsterSDK) {

    private val version = "v3"

    //FIXME: typo on back end ("item" -> "items")
    suspend fun getSubscriptions(): Items<MerchantSubscriptions> {
        return sdk.call<Any, Items<MerchantSubscriptions>>(
            url = "/$version/merchant/subscriptions"
        )
    }

    suspend fun getProfile() : Item<MerchantProfile>{
        return sdk.call<Any,Item<MerchantProfile>>(
            url = "/$version/merchant"
        )
    }


}
