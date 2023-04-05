package com.example.base

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("reason_code") val errorCode: String,
    @SerializedName("reason_description") val reason: String
)
