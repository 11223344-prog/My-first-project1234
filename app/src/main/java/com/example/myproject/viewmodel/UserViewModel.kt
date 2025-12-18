package com.example.myproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myproject.model.UserModel
import com.example.myproject.repository.UserRepo

class UserViewModel(
    private val repo: UserRepo
) : ViewModel() {

    fun register(
        email: String,
        password: String,
        callback: (success: Boolean, message: String, userId: String) -> Unit
    ) {
        repo.register(email, password, callback)
    }

    fun login(
        email: String,
        password: String,
        callback: (success: Boolean, message: String) -> Unit
    ) {
        repo.login(email, password, callback)
    }

    // ✅ ADD USER
    fun addUserToDatabase(
        userId: String,
        user: UserModel,
        callback: (success: Boolean, message: String) -> Unit
    ) {
        repo.addUserToDatabase(userId, user, callback)
    }

    fun forgetPassword(
        email: String,
        callback: (success: Boolean, message: String) -> Unit
    ) {
        repo.forgetPassword(email, callback)
    }
}
