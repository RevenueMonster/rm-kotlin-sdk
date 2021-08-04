package io.revenuemonster.sdk.model.common

import io.revenuemonster.sdk.model.enum.ExpiryType
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Expiry(
    val type: ExpiryType,
    val day: Int? = null,
    val expiredAt: Instant? = null,
)
