package io.revenuemonster.sdk.module

import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.response.UserProfile

class UserModule(private val sdk : RevenueMonsterSDK){
    suspend fun getUserProfile(): Item<UserProfile> {
        return sdk.call<Any, Item<UserProfile>>(
            url = "/user"
        )
    }
}