package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.response.ChopStampResponse
import io.revenuemonster.sdk.model.response.GourmetCardResponse

class CampaignModule(private val sdk: RevenueMonsterSDK) {

    suspend fun giveChopStamp(code: String): Item<ChopStampResponse> {
        return sdk.call(
            url = "/v3/loyalty/chop-stamp/card/scan",
            method = HttpMethod.Post,
            body = mapOf("code" to code)
        )
    }

}