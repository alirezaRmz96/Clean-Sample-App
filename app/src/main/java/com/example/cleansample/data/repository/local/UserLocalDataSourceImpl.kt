package com.example.cleansample.data.repository.local

import com.example.cleansample.data.repository.local.db.AppDao
import com.example.cleansample.data.repository.local.db.UserEntity
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val dao: AppDao
) : UserLocalDataSource {
    override suspend fun insertUsers(users: List<UserEntity>) {
        dao.insertUsers(users)
    }

    override suspend fun getUsers(): List<UserEntity> {
        return dao.getUsers()
    }

}