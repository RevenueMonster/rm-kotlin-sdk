package io.revenuemonster.sdk.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TopUpWalletRequest(
    val redirect : String,
    val amount : Int
)