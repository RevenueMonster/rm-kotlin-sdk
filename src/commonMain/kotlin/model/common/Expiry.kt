package org.rm.sdk.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Expiry(
    val type: ExpiryType,
//    val day: Int? = 0,
//    val expiredAt: Instant?,
)
