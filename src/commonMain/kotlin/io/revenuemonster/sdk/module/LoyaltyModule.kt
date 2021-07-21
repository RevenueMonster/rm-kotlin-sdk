package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.response.RegisterMemberResponse
import io.revenuemonster.sdk.model.request.RegisterMemberRequest

class LoyaltyModule(private val sdk : RevenueMonsterSDK) {

    suspend fun registerLoyaltyMember(data : RegisterMemberRequest) : Item<RegisterMemberResponse> {
        return sdk.call<RegisterMemberRequest, Item<RegisterMemberResponse>>(
            url = "/v3/loyalty/member",
            method = HttpMethod.Post,
            body = data
        )
    }

}