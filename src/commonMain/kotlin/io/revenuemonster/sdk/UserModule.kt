package io.revenuemonster.sdk

import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.response.UserProfile

class UserModule(private val sdk : RevenueMonsterSDK){

    //store id have problem
    suspend fun getUserProfile(): Item<UserProfile> {
        return sdk.call<Any, Item<UserProfile>>(
            url = "/v3/user"
        )
    }

}