package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.Response
import io.revenuemonster.sdk.model.common.MemberProfile
import io.revenuemonster.sdk.model.request.RegisterMemberRequest
import io.revenuemonster.sdk.model.response.CheckMemberResponse
import io.revenuemonster.sdk.model.response.Reward
import io.revenuemonster.sdk.model.response.Voucher
import kotlinx.serialization.json.JsonNull

class MemberModule(private val sdk: RevenueMonsterSDK) {

    // member
    suspend fun registerLoyaltyMember(data: RegisterMemberRequest): Item<MemberProfile> {
        return sdk.call(
            url = "/v3/loyalty/member",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun checkLoyaltyMember(
        countryCode: String,
        phoneNumber: String
    ): Item<CheckMemberResponse> {
        return sdk.call(
            url = "/v3/loyalty/member/check",
            method = HttpMethod.Post,
            body = mapOf(
                "countryCode" to countryCode,
                "phoneNumber" to phoneNumber
            )
        )
    }

    suspend fun getMemberProfile(
        countryCode: String,
        phoneNumber: String
    ): Item<MemberProfile> {
        return sdk.call<Any, Item<MemberProfile>>(
            url = "/v3/loyalty/me?countryCode=$countryCode&phoneNumber=$phoneNumber"
        )
    }

    // member -> vouchers
    suspend fun getVouchers(
        countryCode: String,
        phoneNumber: String
    ): Items<Voucher> {
        return sdk.call<Any, Items<Voucher>>(
            url = "/v3/loyalty/me/vouchers?countryCode=$countryCode&phoneNumber=$phoneNumber"
        )
    }

    suspend fun getVoucherByCode(
        code: String,
        countryCode: String,
        phoneNumber: String
    ): Item<Voucher> {
        return sdk.call<Any, Item<Voucher>>(
            url = "/v3/loyalty/me/voucher/$code?countryCode=$countryCode&phoneNumber=$phoneNumber"
        )
    }

    suspend fun redeemVoucher(
        code: String,
        countryCode: String,
        phoneNumber: String
    ): Item<Voucher> {
        return sdk.call<JsonNull, Item<Voucher>>(
            url = "/v3/loyalty/me/voucher/$code/redeem?countryCode=$countryCode&phoneNumber=$phoneNumber",
            method = HttpMethod.Post
        )
    }

    // member -> rewards
    suspend fun getRewards(
        countryCode: String,
        phoneNumber: String
    ): Items<Reward> {
        return sdk.call<Any, Items<Reward>>(
            url = "/v3/loyalty/me/rewards?countryCode=$countryCode&phoneNumber=$phoneNumber"
        )
    }

    suspend fun getRewardByID(
        rewardId: String,
        countryCode: String,
        phoneNumber: String
    ): Item<Reward> {
        return sdk.call<Any, Item<Reward>>(
            url = "/v3/loyalty/me/reward/$rewardId?countryCode=$countryCode&phoneNumber=$phoneNumber"
        )
    }

    suspend fun redeemReward(
        rewardId: String,
        countryCode: String,
        phoneNumber: String
    ): Response {
        return sdk.call<JsonNull, Response>(
            url = "/v3/loyalty/me/reward/$rewardId/redeem?countryCode=$countryCode&phoneNumber=$phoneNumber",
            method = HttpMethod.Post
        )
    }

}