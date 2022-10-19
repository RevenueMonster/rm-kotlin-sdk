package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.ItemList
import io.revenuemonster.sdk.model.Response
import io.revenuemonster.sdk.model.response.*

class MerchantWalletModule(private val sdk: RevenueMonsterSDK) {

    suspend fun checkBalance(): Item<CheckBalance> {
        return sdk.call<Any, Item<CheckBalance>>(
            url = "/v3/wallet/credit"
        )
    }

    suspend fun topUpWallet(redirect: String, amount : Int): Item<String> {
        val data = TopUpWalletRequest(redirect, amount)
        return sdk.call(
            url = "/v3/wallet/topup",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun checkWalletHistory(cursor: String = ""): ItemList<CheckWalletHistory> {
        return sdk.call<Any, ItemList<CheckWalletHistory>>(
            url = "/v3/wallet/history?cursor=$cursor",
        )
    }

    suspend fun topUpHistory(cursor: String = ""): ItemList<TopUpHistory> {
        return sdk.call<Any, ItemList<TopUpHistory>>(
            url = "/v3/wallet/transaction?cursor=$cursor"
        )
    }

}