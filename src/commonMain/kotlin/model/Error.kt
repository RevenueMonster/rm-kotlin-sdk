package org.rm.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDetail(
    val code: String,
)

@Serializable
data class Error(
    val error: ErrorDetail,
)
