package com.example.myproject.repository

import com.example.myproject.model.UserModel

interface UserRepo {

    fun register(
        email: String,
        password: String,
        callback: (success: Boolean, message: String, userId: String) -> Unit
    )

    fun login(
        email: String,
        password: String,
        callback: (success: Boolean, message: String) -> Unit
    )

    fun addUserToDatabase(
        userId: String, model: UserModel,
        callback: (Boolean, String) -> Unit
    )

    fun forgetPassword(
        email: String,
        callback: (success: Boolean, message: String) -> Unit
    )
}
