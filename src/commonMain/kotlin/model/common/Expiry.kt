package io.revenuemonster.sdk.model.common

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import io.revenuemonster.sdk.model.enum.ExpiryType

@Serializable
data class Expiry(
    val type: ExpiryType,
    val day: Int?=null,
    val expiredAt: Instant?= null,
)
