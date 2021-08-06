package io.revenuemonster.sdk.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CheckMemberResponse(
    val exist: Boolean
)