package com.example.cleansample.data.repository.remote

import com.example.cleansample.data.model.UserResponse

interface UserRemoteDataSource {
    suspend fun getUser(): List<UserResponse>?
}