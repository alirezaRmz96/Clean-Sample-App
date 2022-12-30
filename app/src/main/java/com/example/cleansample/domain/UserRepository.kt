package com.example.cleansample.domain

import com.example.cleansample.core.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserFromLocal(): Flow<ResultWrapper<List<UserDomain>>>
}