package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Response

class NotificationModule(private val sdk : RevenueMonsterSDK) {

    suspend fun sendMerchant(title : String, body: String) : Response{
        return sdk.call(
            url = "/v3/push-notification/merchant",
            method = HttpMethod.Post,
            body = mapOf(
                "title" to title,
                "body" to body
            )
        )
    }

    suspend fun sendStore(storeId: String, title : String, body: String) : Response{
        return sdk.call(
            url = "/v3/push-notification/store/$storeId",
            method = HttpMethod.Post,
            body = mapOf(
                "title" to title,
                "body" to body
            )
        )
    }

    suspend fun sendUser(userId : String, title : String, body: String) : Response{
        return sdk.call(
            url = "/v3/push-notification/user/$userId",
            method = HttpMethod.Post,
            body = mapOf(
                "title" to title,
                "body" to body
            )
        )
    }

}