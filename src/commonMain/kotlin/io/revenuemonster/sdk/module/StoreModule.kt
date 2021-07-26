package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.Response
import io.revenuemonster.sdk.model.common.Store
import io.revenuemonster.sdk.model.request.StoreDetails

class StoreModule(private val sdk : RevenueMonsterSDK) {

    suspend fun getStores(): Items<Store> {
        return sdk.call<Any, Items<Store>>("/stores")
    }

    suspend fun getStoreByID(id : String): Item<Store> {
        return sdk.call<Any,Item<Store>>(
            url = "/store/$id"
        )
    }

    suspend fun createStore(data : StoreDetails) : Item<Store>{
        return sdk.call<StoreDetails,Item<Store>>(
            url = "/store",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun updateStore(storeID : String, data: StoreDetails) : Item<Store>{
        return sdk.call<StoreDetails,Item<Store>>(
            url = "/store/$storeID",
            method = HttpMethod.Patch,
            body = data
        )
    }

    suspend fun deleteStore(storeID : String):Response{
        return sdk.call<Any,Response>(
            url = "/store/$storeID",
            method = HttpMethod.Delete
        )
    }



}