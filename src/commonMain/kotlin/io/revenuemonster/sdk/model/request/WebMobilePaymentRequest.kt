package io.revenuemonster.sdk.model.request

import io.revenuemonster.sdk.model.common.Order
import kotlinx.serialization.Serializable

@Serializable
data class WebMobilePaymentRequest(
    val order: Order,
    val customer: WebMobilePaymentCustomer,
    val method: List<String> = listOf(),
    val type: String,
    val storeId: String,
    val redirectUrl: String,
    val notifyUrl: String,
    val layoutVersion: String
)

@Serializable
data class WebMobilePaymentCustomer(
    val userId: String,
    val email: String? = null,
    val countryCode: String? = null,
    val phoneNumber: String? = null
)


