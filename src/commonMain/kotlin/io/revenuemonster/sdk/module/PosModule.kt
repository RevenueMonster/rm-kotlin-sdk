package io.revenuemonster.sdk.module

import io.ktor.http.*
import io.revenuemonster.sdk.RevenueMonsterSDK
import io.revenuemonster.sdk.model.Item
import io.revenuemonster.sdk.model.Response
import io.revenuemonster.sdk.model.request.*
import io.revenuemonster.sdk.model.response.CardSettlementSummary
import io.revenuemonster.sdk.model.response.Transaction
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PosModule(private val sdk: RevenueMonsterSDK) {

    //if rm app version <2.0 provider is not require
    suspend fun quickPay(data: PosQuickPay): Item<Transaction> {
        return sdk.call(
            url = "/v3/payment/terminal/quickpay",
            method = HttpMethod.Post,
            body = data
        )
    }

    suspend fun cardRefund(terminalId: String, data: PosCardRefundData): Item<Transaction> {
        val body = PosCardRefund(
            terminalId = terminalId,
            type = "REFUND",
            data = data
        )

        return sdk.call(
            url = "/v3/event/terminal",
            method = HttpMethod.Post,
            body = body
        )
    }

    //if rm app version <2.0 provider is not require
    suspend fun settlement(
        terminalId: String,
        printReceipt: Boolean,
        provider: String? = null
    ): CardSettlementSummary {

        val data = PosSettlement(
            terminalId = terminalId,
            type = "SETTLEMENT",
            data = PosSettlementData(
                receiptType = if (printReceipt) 1 else 3,
                provider = provider
            )
        )

        return sdk.call(
            url = "/v3/event/terminal",
            method = HttpMethod.Post,
            body = data
        )

    }

    suspend fun cancel(terminalId: String): Response {
        return sdk.call(
            url = "/v3/event/terminal",
            method = HttpMethod.Post,
            body = mapOf(
                "terminalId" to terminalId,
                "type" to "CANCEL"
            )
        )
    }

}