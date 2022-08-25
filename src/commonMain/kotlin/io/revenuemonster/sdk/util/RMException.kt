package io.revenuemonster.sdk.util

import io.revenuemonster.sdk.model.Error
import io.revenuemonster.sdk.model.ErrorDetail
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class RMException(response : String) : Exception(response){

    private val json = Json {
        this.ignoreUnknownKeys = true
        this.coerceInputValues = true
    }

    private val e = json.decodeFromString<Error>(this.message!!)

    fun getErrorDetail() : ErrorDetail {
        return e.error
    }

    fun errorCode(): String {
        return e.error.code
    }

    fun errorMessage() : String{
        return e.error.message
    }

}