package com.example.cleansample.data.utils

import org.json.JSONObject
import retrofit2.Response

fun <T> Response<T>.bodyOrThrow(): T? {
    if (this.isSuccessful) {
        return body() ?: throw NullPointerException()
    }
    else {
        val gson = this.errorBody()?.string()?.let { JSONObject(it) }
        var errorMessage = String()
        try {

            if (gson != null) {
                errorMessage = gson.getString("message")
            }
        } catch (e: Exception) {

            gson?.getString("message")?.forEach { error ->
                errorMessage += "$error\n"
            }
        }
        throw ServerException(code = this.code(), messageException = errorMessage)
    }

}