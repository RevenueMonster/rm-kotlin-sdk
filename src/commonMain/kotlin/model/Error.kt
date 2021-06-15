package org.rm.sdk.model

import kotlinx.serialization.Serializable

enum class ErrorCode {
    ALIPAY_CHINA_NOT_ACTIVE,
    ACCESS_TOKEN_READ_FAIL,
    ACCOUNT_FROZEN,
    ACCOUNT_INACTIVE,
    ACCOUNT_NOT_FOUND,
    ACCOUNT_SUSPENDED,
    API_ENDPOINT_NOT_FOUND,
    API_INACTIVE,
    AUTH_CODE_EXPIRED,
    AUTH_CODE_INVALID,
    AUTH_CODE_MIS_MATCH,
    AUTH_CODE_USED,
    AUTHORIZATION_HEADER_NOT_ALLOWED,
    BANK_ERROR,
    CLIENT_NOT_SET_PUBLIC_KEY,
    EMPTY_TOKEN,
}

@Serializable
data class ErrorDetail(
    val code: String,
    val message: String,
//    @Contextual val debug: Any?,
)

@Serializable
data class Error(val error: ErrorDetail) : Throwable("rm: error")
