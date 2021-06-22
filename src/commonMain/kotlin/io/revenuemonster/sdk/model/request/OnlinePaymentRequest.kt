package io.revenuemonster.sdk.model.request


data class OnlinePaymentRequest(
    val order: OnlinePaymentRequestOrder,
    val customer: OnlinePaymentRequestCustomer,
    val method: List<String> = listOf(),
    val type: String,
    val storeId: String,
    val redirectUrl: String,
    val notifyUrl: String,
    val layoutVersion: String = "v3"
)

data class OnlinePaymentRequestCustomer(
    val userId: String,
    val email: String,
    val countryCode: String,
    val phoneNumber: String
)

data class OnlinePaymentRequestOrder(
    val id: String,
    val title: String,
    val detail: String,
    val amount: Int,
    val currencyType: String = "MYR",
    val additionalData: String,
)

