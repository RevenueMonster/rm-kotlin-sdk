package io.revenuemonster.sdk

import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.core.*
import io.revenuemonster.sdk.model.Credential
import io.revenuemonster.sdk.model.Error
import io.revenuemonster.sdk.util.Base64Factory
import io.revenuemonster.sdk.util.Signature
import io.revenuemonster.sdk.util.randomString
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.encodeToJsonElement
import kotlin.time.ExperimentalTime

class RevenueMonsterSDK(
    private val config: Config,
) {

    private val oauth2Url: String = domains[config.sandbox]?.get(0) ?: ""
    private val baseUrl: String = domains[config.sandbox]?.get(1) ?: ""

    private val mutex = Mutex()
    private var credential: OAuthCredential? = null

    private companion object {
        private val domains = mapOf<Boolean, Array<String>>(
            true to arrayOf("https://sb-oauth.revenuemonster.my", "https://sb-open.revenuemonster.my"),
            false to arrayOf("https://oauth.revenuemonster.my", "https://open.revenuemonster.my"),
        )
    }

    val Payment: PaymentModule = PaymentModule(this)
    val Merchant: MerchantModule = MerchantModule(this)

    internal suspend inline fun <reified I, reified O> call(
        url: String,
        method: HttpMethod = HttpMethod.Get,
//        headers: HeadersBuilder = HeadersBuilder(),
        body: I? = null,
    ): O {
        try {
            val cred = getAccessToken()
            val uri = baseUrl + url
            var el: JsonElement = JsonNull
            if (body != null) el = normalize(Json.encodeToJsonElement(body))
            val accessToken = cred.accessToken
            val signType = "sha256"
            val timestamp = Clock.System.now().epochSeconds.toString()
            val nonce = randomString(32)
            val signature = Signature.generateSignature(
                data = if (body != null) Json.encodeToString(el) else "",
                privateKey = config.privateKey,
                requestUrl = uri,
                nonceStr = nonce,
                signType = signType,
                method = method.value,
                timestamp = timestamp
            )

            println("URL => $uri")
            println("Method => ${method.value}")
//            println("Body => $data")
            println("AccessToken => $accessToken")
            println("Timestamp => $timestamp")
            println("Signature => $signature")

            return client.request(uri) {
                this.method = method
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.ContentType, "application/json")
                    append(HttpHeaders.Authorization, "Bearer $accessToken")
                    append("X-Signature", "$signType $signature")
                    append("X-Nonce-Str", nonce)
                    append("X-Timestamp", timestamp)
                }
                if (body != null) {
                    this.body = el
                }
            }
        } catch (e: ClientRequestException) {
            throw Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }.decodeFromString(Error.serializer(), e.response.readText())
        } catch (e: SerializationException) {
            println("deserialize")
            throw e
        } catch (e: Exception) {
            throw e
        }
    }

    @OptIn(ExperimentalTime::class)
    private suspend fun getAccessToken(): OAuthCredential {
        if (credential != null && Clock.System.now() < credential!!.expireDateTime) {
            return credential!!
        }

        try {
            var cred: OAuthCredential
            val b64 =
                String(
                    Base64Factory.createEncoder().encode("${config.clientID}:${config.clientSecret}".toByteArray())
                )

            mutex.withLock {
                val item =
                    client.post<Credential>("$oauth2Url/v1/token") {
                        headers {
                            append(HttpHeaders.Accept, "application/json")
                            append(HttpHeaders.ContentType, "application/json")
                            append(HttpHeaders.Authorization, "Basic $b64")
                        }
                        body = mapOf("grantType" to "client_credentials")
                    }

                cred = OAuthCredential(
                    accessToken = item.accessToken,
                    refreshToken = item.refreshToken,
                    expireDateTime = Clock.System.now().plus(item.expiresIn, DateTimeUnit.SECOND)
                )
            }

            return cred
        } catch (e: ClientRequestException) {
            val err = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }.decodeFromString(Error.serializer(), e.response.readText())
            throw err
        } catch (e: Exception) {
            throw e
        }
    }

}
