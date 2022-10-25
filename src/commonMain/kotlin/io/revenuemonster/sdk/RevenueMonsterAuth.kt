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

    //Temporary disable
//    @OptIn(InternalAPI::class)
//    suspend fun refreshToken(refreshToken: String): OAuthCredential {
//        try {
//
//            var cred: OAuthCredential
//            val b64 = "${config.clientID}:${config.clientSecret}".encodeBase64()
//
//            Mutex().withLock {
//                val item =
//                    client.post<Credential>("$baseUrl/v1/token") {
//                        headers {
//                            append(HttpHeaders.ContentType, "application/json")
//                            append(HttpHeaders.Authorization, "Basic $b64")
//                        }
//                        body = mapOf(
//                            "grantType" to "refresh_token",
//                            "refreshToken" to refreshToken
//                        )
//                    }
//                cred = OAuthCredential(
//                    accessToken = item.accessToken,
//                    expiresIn = item.expiresIn,
//                    refreshToken = item.refreshToken,
//                    refreshTokenExpiresIn = item.refreshTokenExpiresIn,
//                    privateKey = config.privateKey,
//                    sandbox = config.sandbox
//                )
//            }
//
//            return cred
//        } catch (e: ClientRequestException) {
//            throw json.decodeFromString(Error.serializer(), e.response.readText())
//        } catch (e: Exception) {
//            throw e
//        }
//    }

}