package com.example.cleansample.core.utils

sealed class ResultWrapper<out T> {
    object Loading : ResultWrapper<Nothing>()
    data class Success<T>(val resultData: T) : ResultWrapper<T>()
    data class Failure(val message: String, val code: Int? = null) : ResultWrapper<Nothing>()
}