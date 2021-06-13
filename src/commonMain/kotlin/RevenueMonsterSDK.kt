package org.rm.sdk

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.core.*
import org.rm.sdk.model.AccessToken
import org.rm.sdk.util.Base64Factory

class RevenueMonsterSDK(
    private val clientID: String,
    private val clientSecret: String,
    private val privateKey: String,
    private val publicKey: String,
    private val sandbox: Boolean = true,
) {
    private companion object {
        val info = "This is info"
        fun getMoreInfo(): String {
            return "This is more fun"
        }
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

    suspend fun getAccessToken() {
        try {
            val b64 = String(Base64Factory.createEncoder().encode("$clientID:$clientSecret".toByteArray()))
            val item: AccessToken =
                client.post("https://sb-oauth.revenuemonster.my/v1/token") {
                    headers {
                        append(HttpHeaders.Accept, "application/json")
                        append(HttpHeaders.ContentType, "application/json")
                        append(HttpHeaders.Authorization, "Basic $b64")
                    }
                    body = mapOf("grantType" to "client_credentials")
                }
            println("debug !!!!!!!!!!!!!!!!!!!")
            println(item)
//            return response
        } catch (e: Exception) {
            println("C")
            print(e.message)
            throw e
        }
    }

    private fun call(url: String): String {
        return url
    }

    fun getString(): String {
        return "test1111"
    }
}
