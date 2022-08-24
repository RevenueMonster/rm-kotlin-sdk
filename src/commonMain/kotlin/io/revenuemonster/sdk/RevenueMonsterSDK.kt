package io.revenuemonster.sdk

import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import io.revenuemonster.sdk.model.Credential
import io.revenuemonster.sdk.model.Error
import io.revenuemonster.sdk.module.*
import io.revenuemonster.sdk.util.Signature
import io.revenuemonster.sdk.util.randomString
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import kotlin.time.ExperimentalTime

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
        try {
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

            return client.request(uri) {
                this.method = method
                headers {
                    append(HttpHeaders.ContentType, "application/json")
                    append(HttpHeaders.Authorization, "Bearer ${oAuth.accessToken}")
                    append("X-Signature", "$signType $signature")
                    append("X-Nonce-Str", nonce)
                    append("X-Timestamp", timestamp)
                }
                if (body != null) {
                    this.body = el
                    println("HttpRequest =>")
                    println(this.body)
                }
            }
        } catch (e: ClientRequestException) {
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
            throw json.decodeFromString(Error.serializer(), e.response.readText())
        } catch (e: SerializationException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
    }

}
