package io.revenuemonster.sdk.model.response

import io.revenuemonster.sdk.model.common.MemberProfile
import kotlinx.serialization.Serializable

@Serializable
data class MemberAuthorizeResponse(
    val profile : MemberProfile,
    val url : String,
)