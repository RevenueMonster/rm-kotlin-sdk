package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class CheckWalletHistoryRequest(
    val cursor: String,
    val transactionAt: List<String>,
    val referenceType: String
)