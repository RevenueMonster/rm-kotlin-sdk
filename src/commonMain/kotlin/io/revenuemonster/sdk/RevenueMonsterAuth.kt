package io.revenuemonster.sdk

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import io.revenuemonster.sdk.model.auth.Credential
import io.revenuemonster.sdk.model.auth.Config
import io.revenuemonster.sdk.model.auth.OAuthCredential
import io.revenuemonster.sdk.util.RMException
import io.revenuemonster.sdk.util.client
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class RevenueMonsterAuth(private val config: Config) {

    private val baseUrl = if (config.sandbox)
        "https://sb-oauth.revenuemonster.my"
    else
        "https://oauth.revenuemonster.my"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    suspend fun getAccessToken(): OAuthCredential {

        val mutex = Mutex()
        var cred: OAuthCredential
        val b64 = "${config.clientID}:${config.clientSecret}".encodeBase64()

        mutex.withLock {
            val response = client.post("$baseUrl/v1/token") {
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Basic $b64")
                }
                setBody(mapOf("grantType" to "client_credentials"))
            }

            if (response.status.isSuccess() && response.status.value == 200) {
                val item = response.body<Credential>()
                cred = OAuthCredential(
                    accessToken = item.accessToken,
                    expiresIn = item.expiresIn,
                    refreshToken = item.refreshToken,
                    refreshTokenExpiresIn = item.refreshTokenExpiresIn,
                    privateKey = config.privateKey,
                    sandbox = config.sandbox
                )

                return cred
            } else {
                throw RMException(response.bodyAsText())
            }

        }
    }

    suspend fun refreshToken(refreshToken: String): OAuthCredential {
        var auth: OAuthCredential
        val b64 = "${config.clientID}:${config.clientSecret}".encodeBase64()
        val mutex = Mutex()

        mutex.withLock {
            val response = client.post("$baseUrl/v1/token") {
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Basic $b64")
                }
                setBody(
                    mapOf(
                        "grantType" to "refresh_token",
                        "refreshToken" to refreshToken
                    )
                )
            }

            if (response.status.isSuccess() && response.status.value == 200) {
                val cred = json.decodeFromString<Credential>(response.bodyAsText())
                auth = OAuthCredential(
                    accessToken = cred.accessToken,
                    expiresIn = cred.expiresIn,
                    refreshToken = cred.refreshToken,
                    refreshTokenExpiresIn = cred.refreshTokenExpiresIn,
                    privateKey = config.privateKey,
                    sandbox = config.sandbox
                )
                return auth
            } else {
                throw RMException(response.bodyAsText())
            }
        }
    }
}