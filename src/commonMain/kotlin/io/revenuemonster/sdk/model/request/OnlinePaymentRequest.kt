package io.revenuemonster.sdk.model.request

import kotlinx.serialization.Serializable

@Serializable
data class OnlinePaymentRequest(
    val order: OnlinePaymentOrder,
    val customer: OnlinePaymentCustomer,
    val method: List<String> = listOf(),
    val type: String,
    val storeId: String,
    val redirectUrl: String,
    val notifyUrl: String,
    val layoutVersion: String = "v3"
)

@Serializable
data class OnlinePaymentCustomer(
    val userId: String,
    val email: String,
    val countryCode: String,
    val phoneNumber: String
)

@Serializable
data class OnlinePaymentOrder(
    val id: String,
    val title: String,
    val detail: String,
    val amount: Int,
    val currencyType: String = "MYR",
    val additionalData: String,
)
