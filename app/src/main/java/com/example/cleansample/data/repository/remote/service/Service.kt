package com.example.cleansample.data.repository.remote.service

import com.example.cleansample.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface Service {
    @GET("users")
    suspend fun getUsers():Response<List<UserResponse>>
}