package io.revenuemonster.sdk.util

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.observer.*

actual val client: HttpClient = HttpClient(CIO) {
    engine {}
    install(JsonFeature) {
        serializer = KotlinxSerializer(
            kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }
    this.ResponseObserver {
        println("HttpResponse => ")
        println(it.receive<String>())
    }
}