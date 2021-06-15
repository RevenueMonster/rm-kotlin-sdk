package org.rm.sdk

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json
import org.rm.sdk.model.Credential
import org.rm.sdk.model.Error
import org.rm.sdk.util.Base64Factory
import org.rm.sdk.util.Signature
import org.rm.sdk.util.randomString

class Config

val client: HttpClient = HttpClient() {
    install(JsonFeature) {
        serializer = KotlinxSerializer(
            kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }
}

class RevenueMonsterSDK(
    private val clientID: String,
    private val clientSecret: String,
    private val privateKey: String,
    private val publicKey: String,
    private val sandbox: Boolean = true,
//    block: Config.() -> Unit = {}
) {
    val oauth2Url: String = domains[sandbox]?.get(0) ?: ""
    val baseUrl: String = domains[sandbox]?.get(1) ?: ""

    private val mutex = Mutex()
    internal var credential: Credential? = null

    private companion object {
        private val domains = mapOf<Boolean, Array<String>>(
            true to arrayOf("https://sb-oauth.revenuemonster.my", "https://sb-open.revenuemonster.my"),
            false to arrayOf("https://oauth.revenuemonster.my", "https://open.revenuemonster.my"),
        )
    }

    val payment: PaymentModule = PaymentModule(this)
    val merchant: MerchantModule = MerchantModule(this)

    internal suspend inline fun <reified T> call(
        url: String,
        requestMethod: HttpMethod = HttpMethod.Get,
//        headers: HeadersBuilder = HeadersBuilder(),
        requestBody: Any? = null,
    ): T {
        try {
            val uri = baseUrl + url
            var data = ""
            val signType = "sha256"
            val timestamp = Clock.System.now().epochSeconds.toString()
            val nonce = randomString(32)
            val signature = Signature.generateSignature(
                data = data,
                privateKey = privateKey,
                requestUrl = uri,
                nonceStr = nonce,
                signType = signType,
                method = requestMethod.value,
                timestamp = timestamp
            )
            val accessToken = credential?.accessToken

            println("URL => $uri")
            println("Method => ${requestMethod.value}")
            println("Body => $json")
            println("AccessToken => $accessToken")
            println("Timestamp => $timestamp")
            println("Signature => $signature")

            return client.request(uri) {
                method = requestMethod
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.ContentType, "application/json")
                    append(HttpHeaders.Authorization, "Bearer $accessToken")
                    append("X-Signature", "$signType $signature")
                    append("X-Nonce-Str", nonce)
                    append("X-Timestamp", timestamp)
                }
            }
        } catch (e: ClientRequestException) {
            println("ClientRequestException!!!")
            println(e)
            throw Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }.decodeFromString(Error.serializer(), e.response.readText())
        } catch (e: Exception) {
            println("Exception!!!")
            println(e)
            throw e
        }
    }

    suspend fun getAccessToken(): Credential {
        try {
            val b64 = String(Base64Factory.createEncoder().encode("$clientID:$clientSecret".toByteArray()))
            val item: Credential =
                client.post<Credential>("$oauth2Url/v1/token") {
                    headers {
                        append(HttpHeaders.Accept, "application/json")
                        append(HttpHeaders.ContentType, "application/json")
                        append(HttpHeaders.Authorization, "Basic $b64")
                    }
                    body = mapOf("grantType" to "client_credentials")
                }

            mutex.withLock {
                credential = item
            }

            return item
        } catch (e: ClientRequestException) {
            println("ClientRequestException !!!")
            println(e)
            val err = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }.decodeFromString(Error.serializer(), e.response.readText())
            println("debug ${err.message}")
            println(e.response.readText())
            throw err
        } catch (e: Exception) {
            println("Exception !!!")
            println(e)
            throw e
        }
    }
}
