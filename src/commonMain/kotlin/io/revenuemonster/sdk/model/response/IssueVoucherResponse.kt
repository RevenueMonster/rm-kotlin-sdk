package io.revenuemonster.sdk.model.response

import kotlinx.serialization.Serializable

@Serializable
data class IssueVoucherResponse(
    val code: String,
    val qrUrl: String
)