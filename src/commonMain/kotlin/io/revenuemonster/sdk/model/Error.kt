package io.revenuemonster.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDetail(
    val code: String,
    val message: String,
)

@Serializable
data class Error(val error: ErrorDetail) : Throwable("rm: error")
