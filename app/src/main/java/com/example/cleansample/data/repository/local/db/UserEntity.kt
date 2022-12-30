package com.example.cleansample.data.repository.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cleansample.data.model.UserResponse
import com.example.cleansample.domain.UserDomain

@Entity(tableName = "users")
data class UserEntity(
    val email: String,
    @PrimaryKey val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) {
    fun toUserDomain(): UserDomain {
        return UserDomain(
            email = email,
            name = name,
            phone = phone,
            username = username,
            website = website,
            id = id
        )
    }
}

fun UserResponse.toUserEntity() = UserEntity(
    email = email,
    id = id,
    phone = phone,
    name = name,
    username = username,
    website = website
)
