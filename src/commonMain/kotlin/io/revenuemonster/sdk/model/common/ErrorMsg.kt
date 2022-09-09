package io.revenuemonster.sdk.model.common

import kotlinx.serialization.Serializable

@Serializable
data class ErrorMsg(
    val message: String? = null
)
