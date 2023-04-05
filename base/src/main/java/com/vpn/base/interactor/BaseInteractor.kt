package com.example.base.interactor

import com.example.base.ErrorResponse
import com.example.base.ResultWrapper
import com.example.base.exceptions.NeedUpdateAppException
import com.google.gson.Gson
import java.io.IOException
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

open class BaseInteractor : CoroutineScope {

    override val coroutineContext: CoroutineContext = Job() + Dispatchers.IO

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultWrapper<T> {
        return withContext(coroutineContext) {
            try {
                val result = apiCall.invoke()
                if (result is Response<*> && (result.code() == 401 && result.code() == 403)) {

                    ResultWrapper.GenericError(null, null)
                } else if (result is Response<*> && result.code() == 503) {
                    ResultWrapper.Maintenance(result.headers().values("x-retry-after").first())
                } else if (result is Response<*> && result.code() >= 400) {
                    val code = result.code()
                    val errorResponse = convertErrorBody(result)
                    ResultWrapper.GenericError(code, errorResponse)
                } else {
                    ResultWrapper.Success(result)
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ResultWrapper.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        if (code == 420) throw NeedUpdateAppException("Need update app exception")
                        if (code == 401) {
                            ResultWrapper.AuthError
                        } else if (code == 503) {
                            ResultWrapper.Maintenance("")
                        } else {
                            val errorResponse = convertErrorBody(throwable)
                            if (isInvalidHeaderError(errorResponse?.errorCode.orEmpty())) {
                                ResultWrapper.AuthError
                            } else {
                                ResultWrapper.GenericError(code, errorResponse)
                            }
                        }
                    }
                    else -> {
                        ResultWrapper.GenericError(null, ErrorResponse("", throwable.localizedMessage))
                    }
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.string()?.let {
                Gson().fromJson(it, ErrorResponse::class.java)
            }
        } catch (exception: Exception) {
            null
        }
    }

    private fun convertErrorBody(response: Response<*>): ErrorResponse? {
        return try {
            response.errorBody()?.string()?.let {
                Gson().fromJson(it, ErrorResponse::class.java)
            }
        } catch (exception: Exception) {
            null
        }
    }

    private fun isInvalidHeaderError(errorCode: String) = errorCode == "2"

}