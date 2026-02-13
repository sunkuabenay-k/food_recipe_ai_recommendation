package com.example.foodrecipe.core.network

import retrofit2.HttpException
import java.io.IOException

object ErrorMapper {

    fun map(throwable: Throwable): ApiResult.Error {
        return when (throwable) {

            is IOException -> ApiResult.Error(
                message = "No internet connection"
            )

            is HttpException -> ApiResult.Error(
                message = throwable.message(),
                code = throwable.code()
            )

            else -> ApiResult.Error(
                message = throwable.localizedMessage ?: "Unknown error"
            )
        }
    }
}
