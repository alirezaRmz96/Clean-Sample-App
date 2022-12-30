package com.example.cleansample.ui

import com.example.cleansample.data.model.UserResponse

data class UserView(
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)
fun UserResponse.toUserResponseItemView () = UserView(
    email = email,
    id=id,
    name = name,
    phone = phone,
    username = username,
    website = website
)
