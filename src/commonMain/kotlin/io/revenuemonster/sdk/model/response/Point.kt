package io.revenuemonster.sdk.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Point(
    val orderAmount: Int,
    val point: Int,
)