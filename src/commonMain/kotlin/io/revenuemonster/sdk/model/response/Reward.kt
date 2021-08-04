package io.revenuemonster.sdk.model.response

import io.revenuemonster.sdk.model.common.Payload
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Reward(
    val id: String,
    val label: String,
    val imageUrl: String,
    val point: Int,
    val payload: Payload,
    val isShipping: Boolean,
    val quantity: Int,
    val balanceQuantity: Int,
    val usedQuantity: Int,
    val redeemQuantity: Int,
    val isEnabled: Boolean,
    val limitPerUser: Int,
    val status: String,
    val isCombo: Boolean,
    val type: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val expiredAt: Instant
)