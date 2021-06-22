package io.revenuemonster.sdk

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

internal val client: HttpClient = HttpClient() {
    engine {
        threadsCount = 2
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer(
            kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
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
