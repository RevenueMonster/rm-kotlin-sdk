package io.revenuemonster.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class Error(val error: ErrorDetail)
