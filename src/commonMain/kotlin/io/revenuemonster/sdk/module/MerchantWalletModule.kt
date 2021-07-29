package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.ItemsC
import io.revenuemonster.sdk.model.response.*

class MerchantWalletModule(private val sdk: RevenueMonsterSDK) {

    suspend fun checkBalance(): Item<CheckBalance> {
        return sdk.call<Any, Item<CheckBalance>>(
            url = "/wallet/credit"
        )
    }

    suspend fun topUpWallet(data: TopUpWalletRequest): Item<String> {
        return sdk.call<TopUpWalletRequest, Item<String>>(
            url = "/wallet/topup",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun checkWalletHistory(): ItemsC<CheckWalletHistory> {
        return sdk.call<Any, ItemsC<CheckWalletHistory>>(
            url = "/wallet/history",

            )
    }

    //FIXME : method (POST) not allow
//    suspend fun checkWalletHistory(data : CheckWalletHistoryRequest) : ItemsC<CheckWalletHistory>{
//        return sdk.call<CheckWalletHistoryRequest, ItemsC<CheckWalletHistory>>(
//            url = "/wallet/history",
//            method = HttpMethod.Post,
//            body = data
//        )
//    }

    suspend fun topUpHistory(): ItemsC<TopUpHistory> {
        return sdk.call<Any, ItemsC<TopUpHistory>>(
            url = "/wallet/transaction"
        )
    }

}