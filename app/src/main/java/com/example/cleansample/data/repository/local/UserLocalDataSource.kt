package com.example.cleansample.data.repository.local

import com.example.cleansample.data.repository.local.db.UserEntity

interface UserLocalDataSource {
    suspend fun insertUsers(users: List<UserEntity>)
    suspend fun getUsers(): List<UserEntity>
}