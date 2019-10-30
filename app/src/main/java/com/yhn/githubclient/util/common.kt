package com.yhn.githubclient.util

import com.yhn.githubclient.data.Result
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

suspend fun <T> apiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        Result.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> Result.Error(throwable)
            is HttpException -> Result.Error(ApiException(throwable.code(), throwable.message()))
            else -> Result.Error(IllegalStateException(""))
        }
    }
}
