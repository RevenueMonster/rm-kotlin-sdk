package io.revenuemonster.sdk.model.common

import kotlinx.serialization.Serializable

@Serializable
data class ExtraInfo(
    val type: String,
    val reference: String
)