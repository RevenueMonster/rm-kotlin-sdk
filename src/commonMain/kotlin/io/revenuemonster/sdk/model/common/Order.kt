package io.revenuemonster.sdk.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val id: String,
    val title: String,
    val detail: String,
    val amount: Int,
    val currencyType: String,
    val additionalData: String,
)