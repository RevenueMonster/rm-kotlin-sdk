
package org.rm.sdk.model.common
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable


@Serializable
data class Expiry(
    val type: ExpiryType,
    val day: Int,
    val expiredAt: Instant,
)