package io.revenuemonster.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDetail(
    val code: String,
    val message: String,
//    @Contextual val debug: Any?,
)

@Serializable
data class Error(val error: ErrorDetail) : Throwable("rm: error")
