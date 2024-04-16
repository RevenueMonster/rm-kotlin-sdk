package io.revenuemonster.sdk.util

import io.revenuemonster.sdk.model.Error
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class RMException(response : String) : Exception(response){

    private val json = Json {
        this.ignoreUnknownKeys = true
        this.coerceInputValues = true
    }

    private val e = json.decodeFromString<Error>(this.message!!)
    val errorCode = e.error.code
    val errorMessage = e.error.message
    val errorResponse = this.message

}