package io.revenuemonster.sdk.model.response

import io.revenuemonster.sdk.model.common.Address
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class MerchantProfile(
    val id: String,
    val companyName: String,
    val companyType: String,
    val companyLogoUrl: String,
    val registrationNumber: String,
    val businessCategory: String,
    val establishedAt: String,
    val countryCode: String,
    val phoneNumber: String,
    val addressLine1: String,
    val addressLine2: String,
    val postcode: String,
    val city: String,
    val state: String,
    val country: String,
    val invoiceAddress: Address,
    val isActive: Boolean,
    val status: String,
    val isMasterMerchant: Boolean,
    val masterMerchantId: String,
    val isPartner: Boolean,
    val partnerId: String,
    val gstNo: String,
    val createdAt: Instant,
    val updatedAt: Instant
)
