package org.rm.sdk.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Expiry(
    val type: String,
    val day: UInt,
    val expiredAt: Instant
)

