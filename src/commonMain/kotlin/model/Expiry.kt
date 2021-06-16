package org.rm.sdk.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Expiry(
    val type: ExpiryType,
    val day: UInt,
    val expiredAt: Instant
)


enum class ExpiryType {
    PERMANENT,
    DYNAMIC,
    FIX
}



