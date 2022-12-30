package com.example.cleansample.domain

data class UserDomain(
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) {
    fun toUserResponseItemDomain(): UserDomain {
        return UserDomain(
            id = id,
            email = email,
            name = name,
            phone = phone,
            username = username,
            website = website
        )
    }
}