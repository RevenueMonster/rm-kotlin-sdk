package org.rm.sdk.model.common

import kotlinx.serialization.Serializable
import org.rm.sdk.model.enum.ExpiryType

@Serializable
data class Expiry(
    val type: ExpiryType,
//    val day: Int? = 0,
//    val expiredAt: Instant?,
)
