package io.revenuemonster.sdk.model.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class MemberTier(
    val key : String,
    val label : String,
    val minimumPoint : Int,
    val totalMember : Int,
    val description : List<String>,
    val createdAt : Instant,
    val updatedAt : Instant

)