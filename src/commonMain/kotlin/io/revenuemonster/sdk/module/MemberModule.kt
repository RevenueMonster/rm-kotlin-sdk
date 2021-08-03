package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.Response
import io.revenuemonster.sdk.model.common.MemberProfile
import io.revenuemonster.sdk.model.request.CheckMemberRequest
import io.revenuemonster.sdk.model.request.RegisterMemberRequest
import io.revenuemonster.sdk.model.response.CheckMemberResponse
import io.revenuemonster.sdk.model.response.Reward
import io.revenuemonster.sdk.model.response.Voucher
import io.revenuemonster.sdk.model.response.Vouchers
import kotlinx.serialization.json.JsonNull

class MemberModule(private val sdk: RevenueMonsterSDK) {

    //member
    //FIXME : email didnt insert into db (backend)
    suspend fun registerLoyaltyMember(data: RegisterMemberRequest): Item<MemberProfile> {
        return sdk.call<RegisterMemberRequest, Item<MemberProfile>>(
            url = "/loyalty/member",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun checkLoyaltyMember(countryCode: String, phoneNumber: String): Item<CheckMemberResponse> {
        val data = CheckMemberRequest(countryCode, phoneNumber)
        return sdk.call<CheckMemberRequest, Item<CheckMemberResponse>>(
            url = "/loyalty/member/check",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun getMemberProfile(countryCode: String, phoneNumber: String): Item<MemberProfile> {
        return sdk.call<Any, Item<MemberProfile>>(
            url = "/loyalty/me?countryCode=$countryCode&phoneNumber=$phoneNumber"
        )
    }

    //member -> vouchers
    suspend fun getVouchers(countryCode: String, phoneNumber: String): Items<Voucher> {
        return sdk.call<Any, Items<Voucher>>(
            url = "/loyalty/me/vouchers?countryCode=$countryCode&phoneNumber=$phoneNumber"
        )
    }

    suspend fun getVouchersByCode(code: String, countryCode: String, phoneNumber: String): Item<Voucher> {
        return sdk.call<Any, Item<Voucher>>(
            url = "/loyalty/me/voucher/$code?countryCode=$countryCode&phoneNumber=$phoneNumber"
        )
    }

    suspend fun redeemVoucher(code: String, countryCode: String, phoneNumber: String): Item<Voucher> {
        return sdk.call<JsonNull, Item<Voucher>>(
            url = "/loyalty/me/voucher/$code/redeem?countryCode=$countryCode&phoneNumber=$phoneNumber",
            method = HttpMethod.Post
        )
    }
    //member -> rewards
    //FIXME : End point not found
    suspend fun getRewards(countryCode: String, phoneNumber: String): Items<Reward> {
        return sdk.call<Any, Items<Reward>>(
            url = "/loyalty/me/reward?countryCode=$countryCode&phoneNumber=$phoneNumber"
        )
    }

    suspend fun getRewardByID(rewardId: String, countryCode: String, phoneNumber: String): Item<Reward> {
        return sdk.call<Any, Item<Reward>>(
            url = "/loyalty/me/reward/$rewardId?countryCode=$countryCode&phoneNumber=$phoneNumber"
        )
    }

    suspend fun redeemReward(rewardId: String, countryCode: String, phoneNumber: String): Response {
        return sdk.call<JsonNull, Response>(
            url = "/loyalty/me/reward/$rewardId/redeem?countryCode=$countryCode&phoneNumber=$phoneNumber",
            method = HttpMethod.Post
        )
    }

}