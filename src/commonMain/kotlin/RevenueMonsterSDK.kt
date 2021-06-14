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
import kotlinx.serialization.json.Json
import org.rm.sdk.model.Credential
import org.rm.sdk.model.Error
import org.rm.sdk.util.Base64Factory

class RevenueMonsterSDK(
    private val clientID: String,
    private val clientSecret: String,
    private val privateKey: String,
    private val publicKey: String,
    private val sandbox: Boolean = true,
) {
    private val oauth2Url: String = domains[sandbox]?.get(0) ?: ""
    private val baseUrl: String = domains[sandbox]?.get(1) ?: ""

    private var credential: Credential? = null
    private val mutex = Mutex()

    private companion object {
        private val domains = mapOf<Boolean, Array<String>>(
            true to arrayOf("https://sb-oauth.revenuemonster.my", "https://sb-api.revenuemonster.my"),
            false to arrayOf("https://oauth.revenuemonster.my", "https://api.revenuemonster.my"),
        )
    }

    val payment: PaymentModule = PaymentModule(this)
    val merchant: MerchantModule = MerchantModule(this)
    val client: HttpClient by lazy {
        HttpClient() {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    protected fun call(
        headers: HttpHeaders,
        body: Any,
    ) {
    }

    suspend fun getAccessToken(): Credential {
        try {
            val b64 = String(Base64Factory.createEncoder().encode("$clientID:$clientSecret".toByteArray()))
            val item: Credential =
                client.post<Credential>("$oauth2Url/v1/token") {
                    headers {
                        append(HttpHeaders.Accept, "application/json")
                        append(HttpHeaders.ContentType, "application/json")
                        append(HttpHeaders.Authorization, "Basic s$b64")
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
//            println(e.response.status)
//            println(e.cause)
            throw err
        } catch (e: Exception) {
            println("debug 2")
            throw e
        }
    }

    fun getString(): String {
        return "test1111"
    }
}
