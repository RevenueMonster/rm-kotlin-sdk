package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.Response
import io.revenuemonster.sdk.model.common.LoyaltyMember
import io.revenuemonster.sdk.model.request.*
import io.revenuemonster.sdk.model.response.*

class LoyaltyModule(private val sdk: RevenueMonsterSDK) {

    // loyalty members
    suspend fun memberAuthorize(
        countryCode: String,
        phoneNumber: String
    ): MemberAuthorizeResponse {
        return sdk.call(
            url = "/v3/loyalty/member/authorize",
            method = HttpMethod.Post,
            body = mapOf(
                "countryCode" to countryCode,
                "phoneNumber" to phoneNumber
            )
        )
    }

    suspend fun getLoyaltyMembers(): Items<LoyaltyMember> {
        return sdk.call<Any, Items<LoyaltyMember>>(
            url = "/v3/loyalty/members"
        )
    }

    suspend fun getLoyaltyMemberByID(id: String): Item<LoyaltyMember> {
        return sdk.call<Any, Item<LoyaltyMember>>(
            url = "/v3/loyalty/member/$id"
        )
    }

    suspend fun getLoyaltyMemberHistory(id: String): Items<LoyaltyMemberHistory> {
        return sdk.call<Any, Items<LoyaltyMemberHistory>>(
            url = "/v3/loyalty/member/$id/history"
        )
    }

    suspend fun topUpBalanceOnline(
        memberId: String,
        redirectUrl : String,
        amount : Int,
    ): Item<TopUpBalanceOnlineResponse> {
        val data = TopUpBalanceOnlineRequest(redirectUrl,amount)
        return sdk.call(
            url = "/v3/loyalty/member/$memberId/topup-online",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun topUpBalanceOffline(
        data: TopUpBalanceOfflineRequest
    ): Item<TopUpBalanceOfflineResponse> {
        return sdk.call(
            url = "/v3/loyalty/member/${data.memberId}/topup-offline",
            method = HttpMethod.Post,
            body = data
        )
    }

    //loyalty points
    suspend fun giveLoyaltyPointByPhoneNumber(
        point: Int,
        countryCode: String,
        phoneNumber: String
    ): Response {
        val data = GiveLoyaltyPointRequest(
            point = point,
            type = "PHONENUMBER",
            countryCode = countryCode,
            phoneNumber = phoneNumber
        )
        return sdk.call(
            url = "/v3/loyalty/reward",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun giveLoyaltyPointByQR(point: Int): Item<QRUrl> {
        val data = GiveLoyaltyPointRequest(
            point = point,
            type = "QRCODE"
        )
        return sdk.call(
            url = "/v3/loyalty/reward",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun giveLoyaltyPointByMemberID(point: Int, memberId: String): Response {
        val data = GiveLoyaltyPointRequest(
            point = point,
            type = "ID",
            memberId = memberId
        )
        return sdk.call(
            url = "/v3/loyalty/reward",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun spendingLoyaltyPointByPhoneNumber(
        amount: Int,
        countryCode: String,
        phoneNumber: String
    ): Response {
        val data = SpendingLoyaltyPointRequest(
            currencyType = "MYR",
            amount = amount,
            type = "PHONENUMBER",
            countryCode = countryCode,
            phoneNumber = phoneNumber
        )
        return sdk.call(
            url = "/v3/loyalty/spending-reward",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun spendingLoyaltyPointByQR(amount: Int): Item<QRUrl> {
        val data = SpendingLoyaltyPointRequest(
            currencyType = "MYR",
            amount = amount,
            type = "QRCODE"
        )
        return sdk.call(
            url = "/v3/loyalty/spending-reward",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun spendingLoyaltyPointByMemberID(amount: Int, memberId: String): Response {
        val data = SpendingLoyaltyPointRequest(
            currencyType = "MYR",
            amount = amount,
            type = "ID",
            memberId = memberId
        )
        return sdk.call(
            url = "/v3/loyalty/spending-reward",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun cancelSpendingLoyaltyPoint(id: String): Response {
        return sdk.call(
            url = "/v3/loyalty/spending-reward/cancel",
            method = HttpMethod.Post,
            body = mapOf("id" to id)
        )
    }

    suspend fun calculateSpendingReward(amount: Int): Item<Point> {
        val data = CalculateSpendingRewardRequest("MYR", amount)
        return sdk.call(
            url = "/v3/loyalty/spending-reward/calculate",
            method = HttpMethod.Post,
            body = data
        )
    }

}