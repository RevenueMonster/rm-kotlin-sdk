package org.rm.sdk.model.common

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import org.rm.sdk.model.enum.ExpiryType

@Serializable
data class Expiry(
    val type: ExpiryType,
    val day: Int?=null,
    val expiredAt: Instant?= null,
)
