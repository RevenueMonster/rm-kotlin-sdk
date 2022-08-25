package io.revenuemonster.sdk

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.revenuemonster.sdk.model.auth.OAuthCredential
import io.revenuemonster.sdk.module.*
import io.revenuemonster.sdk.util.RMException
import io.revenuemonster.sdk.util.Signature
import io.revenuemonster.sdk.util.randomString
import kotlinx.datetime.Clock
import kotlinx.serialization.json.*

class RevenueMonsterSDK(
    private val oAuth: OAuthCredential,
) {

    private val baseUrl: String =
        if (oAuth.sandbox) "https://sb-open.revenuemonster.my" else "https://open.revenuemonster.my"
    private val version: String = "/v3"

    val Payment: PaymentModule = PaymentModule(this)
    val Merchant: MerchantModule = MerchantModule(this)
    val Store: StoreModule = StoreModule(this)
    val User: UserModule = UserModule(this)
    val MerchantWallet: MerchantWalletModule = MerchantWalletModule(this)
    val Loyalty: LoyaltyModule = LoyaltyModule(this)
    val Member: MemberModule = MemberModule(this)
    val Voucher: VoucherModule = VoucherModule(this)
    val Campaign: CampaignModule = CampaignModule(this)

    internal suspend inline fun <reified I, reified O> call(
        url: String,
        method: HttpMethod = HttpMethod.Get,
        body: I? = null,
    ): O {
        val uri = baseUrl + version + url
        var el: JsonElement = JsonNull
        if (body != null) el = normalize(Json.encodeToJsonElement(body))
        val signType = "sha256"
        val timestamp = Clock.System.now().epochSeconds.toString()
        val nonce = randomString(32)
        val signature = Signature.generateSignature(
            data = if (body != null) el.toString() else "",
            privateKey = oAuth.privateKey,
            requestUrl = uri,
            nonceStr = nonce,
            signType = signType,
            method = method.value,
            timestamp = timestamp
        )

        val response = client.request(uri) {
            this.method = method
            headers {
                contentType(ContentType.Application.Json)
                append(HttpHeaders.Authorization, "Bearer ${oAuth.accessToken}")
                append("X-Signature", "$signType $signature")
                append("X-Nonce-Str", nonce)
                append("X-Timestamp", timestamp)
            }
            if (body != null) {
                setBody(el)
            }
        }

        if (response.status.isSuccess() && response.status.value == 200) {
            return response.body()
        } else {
            throw RMException(response.bodyAsText())
        }
    }

}
