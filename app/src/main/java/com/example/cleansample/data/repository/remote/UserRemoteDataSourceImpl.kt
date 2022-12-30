package com.example.cleansample.data.repository.remote

import com.example.cleansample.data.model.UserResponse
import com.example.cleansample.data.repository.remote.service.Service
import com.example.cleansample.data.utils.bodyOrThrow
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val service: Service
) : UserRemoteDataSource {
    override suspend fun getUser(): List<UserResponse>? {
        return service.getUsers().bodyOrThrow()
    }
}