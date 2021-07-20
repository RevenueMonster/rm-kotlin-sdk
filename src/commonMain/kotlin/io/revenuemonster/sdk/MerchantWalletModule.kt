package io.revenuemonster.sdk

import io.ktor.http.*
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.ItemsC
import io.revenuemonster.sdk.model.response.*

class MerchantWalletModule(private val sdk: RevenueMonsterSDK) {

    private val version = "v3"

    suspend fun checkBalance() : Item<CheckBalance> {
        return sdk.call<Any,Item<CheckBalance>>(
            url = "/$version/wallet/credit"
        )
    }

    suspend fun topUpWallet(data : TopUpWalletRequest) : Item<String>{
        return sdk.call<TopUpWalletRequest, Item<String>>(
            url = "/$version/wallet/topup",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun checkWalletHistory() : ItemsC<CheckWalletHistory>{
        return sdk.call<Any, ItemsC<CheckWalletHistory>>(
            url = "/$version/wallet/history",

        )
    }

    //method not allow
//    suspend fun checkWalletHistory(data : CheckWalletHistoryRequest) : ItemsC<CheckWalletHistory>{
//        return sdk.call<CheckWalletHistoryRequest, ItemsC<CheckWalletHistory>>(
//            url = "/$version/wallet/history",
//            method = HttpMethod.Post,
//            body = data
//        )
//    }

    suspend fun topUpHistory() : ItemsC<TopUpHistory>{
        return sdk.call<Any, ItemsC<TopUpHistory>>(
            url = "/$version/wallet/transaction"
        )
    }


}