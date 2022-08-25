package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Items
import io.revenuemonster.sdk.model.response.IssueVoucherResponse
import io.revenuemonster.sdk.model.response.Voucher
import io.revenuemonster.sdk.model.response.Vouchers
import kotlinx.serialization.json.JsonNull

class VoucherModule(private val sdk: RevenueMonsterSDK) {
    suspend fun getVoucherBatches(): Items<Vouchers> {
        return sdk.call<Any, Items<Vouchers>>(
            url = "/v3/voucher-batches"
        )
    }

    suspend fun getVoucherByCode(code: String): Item<Voucher> {
        return sdk.call<Any, Item<Voucher>>(
            url = "/v3/voucher/$code"
        )
    }

    suspend fun getVoucherBatchByKey(key: String): Items<Voucher> {
        return sdk.call<Any, Items<Voucher>>(
            url = "/v3/voucher-batch/$key/vouchers"
        )
    }

    suspend fun issueVoucher(key: String): Item<IssueVoucherResponse> {
        return sdk.call<JsonNull, Item<IssueVoucherResponse>>(
            url = "/v3/voucher-batch/$key/issue",
            method = HttpMethod.Post,
        )
    }

    suspend fun voidVoucher(code: String): Item<Voucher> {
        return sdk.call<JsonNull, Item<Voucher>>(
            url = "/v3/voucher/$code/void",
            method = HttpMethod.Post
        )
    }

}