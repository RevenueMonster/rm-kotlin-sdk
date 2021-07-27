package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Response
import io.revenuemonster.sdk.model.request.GourmetCardRequest
import io.revenuemonster.sdk.model.response.ChopStampResponse
import io.revenuemonster.sdk.model.response.GourmetCardResponse

class CampaignModule(private val sdk : RevenueMonsterSDK) {

    suspend fun giveChopStamp(code : String) : Item<ChopStampResponse>{
        //reuse dont mind me 🍺
        val data = Response(code = code)
        return sdk.call<Response,Item<ChopStampResponse>>(
            url = "/loyalty/chop-stamp/card/scan",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun gourmetCard(data : GourmetCardRequest) : Item<GourmetCardResponse>{
        return sdk.call<GourmetCardRequest,Item<GourmetCardResponse>>(
            url = "/membership/verify",
            method = HttpMethod.Post,
            body = data
        )
    }

}