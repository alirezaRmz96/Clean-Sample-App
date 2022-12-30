package com.example.cleansample.data.utils

import com.example.cleansample.core.utils.ResultWrapper

suspend fun <T> safeCall(call: suspend () -> T): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(call.invoke())
    }catch (e:Exception){
        when (e) {
            is ServerException -> ResultWrapper.Failure(
                message = e.messageException,
                code = e.code
            )
            else -> {
                ResultWrapper.Failure(message = e.message.toString())
            }
        }
    }
}