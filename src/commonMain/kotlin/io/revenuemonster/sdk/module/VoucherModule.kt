package io.revenuemonster.sdk.module

import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.response.Voucher
import io.revenuemonster.sdk.model.response.Vouchers

class VoucherModule(private val sdk : RevenueMonsterSDK) {

    //FIXME : docs missing attribute
    suspend fun getVoucherBatches() : Items<Vouchers>{
        return sdk.call<Any,Items<Vouchers>>(
            url = "/voucher-batches"
        )
    }

    suspend fun getVoucherByCode(code: String):Item<Voucher>{
        return sdk.call<Any, Item<Voucher>>(
            url = "/voucher/$code"
        )
    }

    suspend fun getVoucherBatchByKey(key : String): Items<Voucher>{
        return sdk.call<Any,Items<Voucher>>(
            url = "/voucher-batch/$key/vouchers"
        )
    }



}