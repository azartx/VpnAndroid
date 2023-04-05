package com.example.base

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null) :
        ResultWrapper<Nothing>()

    data class Maintenance<out T>(val timeLeft: String) : ResultWrapper<T>()

    object AuthError : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
}