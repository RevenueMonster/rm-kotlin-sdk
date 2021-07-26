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

    //FIXME : email didnt insert into db (backend)
    suspend fun registerLoyaltyMember(data : RegisterMemberRequest) : Item<MemberProfile> {
        return sdk.call<RegisterMemberRequest, Item<MemberProfile>>(
            url = "/loyalty/member",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun checkLoyaltyMember(countryCode : String,phoneNumber : String): Item<CheckMemberResponse>{
        val data = CheckMemberRequest(countryCode,phoneNumber)
        return sdk.call<CheckMemberRequest, Item<CheckMemberResponse>>(
            url = "/loyalty/member/check",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun getMemberProfile(countryCode : String,phoneNumber : String) : Item<MemberProfile>{
        return sdk.call<Any, Item<MemberProfile>>(
            url = "/loyalty/me?countryCode=$countryCode&phoneNumber=$phoneNumber"
        )
    }

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

    suspend fun topUpBalanceOffline(memberId: String,data : TopUpBalanceOfflineRequest): Item<TopUpBalanceOfflineResponse>{
        return sdk.call<TopUpBalanceOfflineRequest,Item<TopUpBalanceOfflineResponse>>(
            url = "/loyalty/member/$memberId/topup-offline",
            method = HttpMethod.Post,
            body = data
        )
    }


}