package io.revenuemonster.sdk.model.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class ChopStampResponse(
    val id: String,
    val key: String,
    val noOfChoppedStamp: Int,
    val isCompleted: Boolean,
    val startAt: Instant,
    val endAt: Instant,
    val createdAt: Instant,
    val updatedAt: Instant
)