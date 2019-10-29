package com.yhn.githubclient.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yhn.githubclient.data.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.lang.IllegalArgumentException
import java.nio.charset.Charset
import java.util.*


suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): Result<T> {
    return withContext(dispatcher) {
        try {
            Result.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> Result.Error(throwable)
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    Result.Error(IllegalArgumentException(errorResponse.toString()))
                }
                else -> {
                    Result.Error(IllegalStateException(""))
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            Gson().fromJson(it.readString(Charset.defaultCharset()), ErrorResponse::class.java)
        }
    } catch (exception: Exception) {
        exception.printStackTrace()
        null
    }
}

data class ErrorResponse(val s: String) {

}
