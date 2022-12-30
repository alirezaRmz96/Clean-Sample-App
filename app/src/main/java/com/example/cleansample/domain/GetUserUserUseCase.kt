package com.example.cleansample.domain

import com.example.cleansample.core.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
     operator fun invoke(): Flow<ResultWrapper<List<UserDomain>>> {
        return userRepository.getUserFromLocal()
    }
}