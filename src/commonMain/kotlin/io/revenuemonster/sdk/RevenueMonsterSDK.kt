package io.revenuemonster.sdk

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import io.revenuemonster.sdk.model.auth.OAuthCredential
import io.revenuemonster.sdk.module.*
import io.revenuemonster.sdk.util.RMException
import io.revenuemonster.sdk.util.Signature
import io.revenuemonster.sdk.util.client
import io.revenuemonster.sdk.util.randomString
import kotlinx.datetime.Clock
import kotlinx.serialization.json.*
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec

class RevenueMonsterSDK(
    private val oAuth: OAuthCredential,
) {

    private val baseUrl: String =
        if (oAuth.sandbox) "https://sb-open.revenuemonster.my" else "https://open.revenuemonster.my"

    val payment: PaymentModule = PaymentModule(this)
    val merchant: MerchantModule = MerchantModule(this)
    val store: StoreModule = StoreModule(this)
    val user: UserModule = UserModule(this)
    val merchantWallet: MerchantWalletModule = MerchantWalletModule(this)
    val loyalty: LoyaltyModule = LoyaltyModule(this)
    val member: MemberModule = MemberModule(this)
    val voucher: VoucherModule = VoucherModule(this)
    val campaign: CampaignModule = CampaignModule(this)
    val pos: PosModule = PosModule(this)
    val notification: NotificationModule = NotificationModule(this)

    internal suspend inline fun <reified I, reified O> call(
        url: String,
        method: HttpMethod = HttpMethod.Get,
        body: I? = null,
    ): O {
        val uri = baseUrl + url
        val el = if (body != null) normalize(Json.encodeToJsonElement(body)) else JsonNull
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

    private fun normalize(elem: JsonElement): JsonElement {
        return when (elem) {
            is JsonObject -> JsonObject(
                elem.entries.map { it.key to normalize(it.value) }.sortedBy { it.first }.toMap()
            )

            is JsonArray -> JsonArray(elem.map { normalize(it) })
            else -> {
                elem
            }
        }
    }

}