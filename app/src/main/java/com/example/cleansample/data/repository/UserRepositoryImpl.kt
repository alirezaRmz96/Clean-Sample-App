package com.example.cleansample.data.repository

import com.example.cleansample.core.utils.ResultWrapper
import com.example.cleansample.data.repository.local.db.toUserEntity
import com.example.cleansample.data.repository.local.UserLocalDataSource
import com.example.cleansample.data.repository.remote.UserRemoteDataSource
import com.example.cleansample.data.utils.safeCall
import com.example.cleansample.domain.UserDomain
import com.example.cleansample.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    override fun getUserFromLocal(): Flow<ResultWrapper<List<UserDomain>>> = flow {
        emit(ResultWrapper.Loading)

        when (val safe = safeCall { remoteDataSource.getUser()}){
            ResultWrapper.Loading ->
            {
                emit(ResultWrapper.Loading)
            }
            is ResultWrapper.Success ->{
                safe.resultData?.let {
                    localDataSource.insertUsers(it.map {
                        it.toUserEntity()
                    })
                }
            }
            is ResultWrapper.Failure ->{
                ResultWrapper.Failure(
                    message = safe.message,
                    code = safe.code
                )
            }
        }
        val user = localDataSource.getUsers().map {
            it.toUserDomain()
        }
        emit(ResultWrapper.Success(user))

    }
}