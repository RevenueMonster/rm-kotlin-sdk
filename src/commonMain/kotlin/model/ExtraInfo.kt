package org.rm.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class ExtraInfo(
    val type : String,
    val reference : String
)