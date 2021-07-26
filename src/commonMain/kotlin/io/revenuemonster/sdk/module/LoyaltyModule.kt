package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.common.LoyaltyMember
import io.revenuemonster.sdk.model.request.CheckMemberRequest
import io.revenuemonster.sdk.model.common.MemberProfile
import io.revenuemonster.sdk.model.request.RegisterMemberRequest
import io.revenuemonster.sdk.model.request.TopUpBalanceOfflineRequest
import io.revenuemonster.sdk.model.request.TopUpBalanceOnlineRequest
import io.revenuemonster.sdk.model.response.*

class LoyaltyModule(private val sdk : RevenueMonsterSDK) {


    //loyalty members
    suspend fun memberAuthorize(countryCode : String,phoneNumber : String) : MemberAuthorizeResponse{
        val data = CheckMemberRequest(countryCode,phoneNumber)
        return sdk.call<CheckMemberRequest, MemberAuthorizeResponse>(
            url = "/loyalty/member/authorize",
            method = HttpMethod.Post,
            body = data
        )
    }

    //FIXME: member tier
    suspend fun getLoyaltyMembers() : Items<LoyaltyMember>{
        return sdk.call<Any, Items<LoyaltyMember>>(
            url = "/loyalty/members"
        )
    }

    suspend fun getLoyaltyMemberByID(id : String): Item<LoyaltyMember>{
        return sdk.call<Any,Item<LoyaltyMember>>(
            url = "/loyalty/member/$id"
        )
    }

    suspend fun getLoyaltyMemberHistory(id : String): Items<LoyaltyMemberHistory>{
        return sdk.call<Any, Items<LoyaltyMemberHistory>>(
            url = "/loyalty/member/$id/history"
        )
    }

    suspend fun topUpBalanceOnline(memberId: String, data: TopUpBalanceOnlineRequest): Item<TopUpBalanceOnlineResponse> {
        return sdk.call<TopUpBalanceOnlineRequest, Item<TopUpBalanceOnlineResponse>>(
            url = "/loyalty/member/$memberId/topup-online",
            method = HttpMethod.Post,
            body = data
        )
    }

    //FIXME : docs https://doc.revenuemonster.my/docs/campaign/loyalty/loyalty-members/topup-offline
    suspend fun topUpBalanceOffline(data : TopUpBalanceOfflineRequest): Item<TopUpBalanceOfflineResponse>{
        return sdk.call<TopUpBalanceOfflineRequest,Item<TopUpBalanceOfflineResponse>>(
            url = "/loyalty/member/${data.memberId}/topup-offline",
            method = HttpMethod.Post,
            body = data
        )
    }

    //loyalty points


}