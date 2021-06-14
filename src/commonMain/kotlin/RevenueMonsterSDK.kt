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
import org.rm.sdk.util.randomString

class Config

val client: HttpClient = HttpClient() {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}

class RevenueMonsterSDK(
    private val clientID: String,
    private val clientSecret: String,
    private val privateKey: String,
    private val publicKey: String,
    private val sandbox: Boolean = true,
    block: Config.() -> Unit = {}
) {
    val oauth2Url: String = domains[sandbox]?.get(0) ?: ""
    val baseUrl: String = domains[sandbox]?.get(1) ?: ""

    private val mutex = Mutex()
    private var credential: Credential? = null

    private companion object {
        private val domains = mapOf<Boolean, Array<String>>(
            true to arrayOf("https://sb-oauth.revenuemonster.my", "https://sb-open.revenuemonster.my"),
            false to arrayOf("https://oauth.revenuemonster.my", "https://open.revenuemonster.my"),
        )
    }

    val payment: PaymentModule = PaymentModule(this)
    val merchant: MerchantModule = MerchantModule(this)

    suspend inline fun <reified T> call(
        url: String,
        method: HttpMethod = HttpMethod.Get,
//        headers: HeadersBuilder = HeadersBuilder(),
        body: Any? = null,
    ): T {
        try {
            val nonce = randomString(32)
            val timestamp = Clock.System.now().epochSeconds.toString()

            return client.request("$baseUrl$url") {
                method
                headers {
                    append(HttpHeaders.Accept, "application/json")
                    append(HttpHeaders.ContentType, "application/json")
                    append("X-Signature", "sha256")
                    append("X-Nonce-Str", nonce)
                    append("X-Timestamp", timestamp)
//                    append(HttpHeaders.Authorization, "Bearer $accessToken")
                }
                body
            }
        } catch (e: ClientRequestException) {
            throw Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }.decodeFromString(Error.serializer(), e.response.readText())
        } catch (e: Exception) {
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
            println("debug 1")
            val err = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }.decodeFromString(Error.serializer(), e.response.readText())
            println("debug ${err.message}")
            println(e.response.readText())
            throw err
        } catch (e: Exception) {
            println("debug 2")
            throw e
        }
    }
}
