package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.common.MemberProfile
import io.revenuemonster.sdk.model.request.CheckMemberRequest
import io.revenuemonster.sdk.model.request.RegisterMemberRequest
import io.revenuemonster.sdk.model.response.CheckMemberResponse

class MemberModule(private val sdk : RevenueMonsterSDK) {


    //member
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

    //member -> vouchers


    //member -> rewards




}