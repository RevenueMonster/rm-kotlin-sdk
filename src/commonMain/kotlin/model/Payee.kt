package org.rm.sdk.model

import kotlinx.serialization.Serializable

@Serializable
data class Payee(
    val userId : String
)