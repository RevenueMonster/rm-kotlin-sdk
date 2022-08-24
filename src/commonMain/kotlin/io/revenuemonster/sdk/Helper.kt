package io.revenuemonster.sdk

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

internal val client: HttpClient = HttpClient(OkHttp) {
    engine {
        threadsCount = 4
    }

    install(ContentNegotiation){
        json(
            Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }

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

internal fun normalize(elem: JsonElement): JsonElement {
    return when (elem) {
        is JsonObject -> JsonObject(
            elem.entries.map { it.key to normalize(it.value) }.sortedBy { it.first }.toMap()
        )
        is JsonArray -> JsonArray(elem.map { normalize(it) })
        else -> {
            elem
        }
    }
}
