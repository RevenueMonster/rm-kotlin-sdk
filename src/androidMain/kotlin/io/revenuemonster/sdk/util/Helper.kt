package io.revenuemonster.sdk.util

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual val client: HttpClient = HttpClient(OkHttp) {
    engine {
        threadsCount = 4
    }

    install(ContentNegotiation) {
        json(
            Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }

//    install(HttpTimeout) {
//        // Set max timeout
//        requestTimeoutMillis = 95000
//        socketTimeoutMillis = 60000
//    }

    ResponseObserver {
        println()
        println("URL ->")
        println(it.request.url)
        println("Header ->")
        println(it.request.headers)
        println("Content ->")
        println(it.request.content)
        println("Status ->")
        println("${it.status.value} ${it.status.description}")
        println("Response ->")
        println(it.bodyAsText())
        println()
    }
}