package com.example.myproject.repository

import com.example.myproject.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class UserRepoImpl : UserRepo {

    private val auth = FirebaseAuth.getInstance()
    private val database =
        FirebaseDatabase.getInstance().getReference("users")

    // ---------- REGISTER ----------
    override fun register(
        email: String,
        password: String,
        callback: (Boolean, String, String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val userId = auth.currentUser?.uid ?: ""
                callback(true, "Registration successful", userId)
            }
            .addOnFailureListener {
                callback(false, it.message ?: "Registration failed", "")
            }
    }

    // ---------- LOGIN ----------
    override fun login(
        email: String,
        password: String,
        callback: (Boolean, String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                callback(true, "Login successful")
            }
            .addOnFailureListener {
                callback(false, it.message ?: "Login failed")
            }
    }

    // ---------- ADD USER TO DATABASE ----------
    override fun addUserToDatabase(
        userId: String,
        user: UserModel,
        callback: (Boolean, String) -> Unit
    ) {
        database.child(userId).setValue(user)
            .addOnSuccessListener {
                callback(true, "User saved successfully")
            }
            .addOnFailureListener {
                callback(false, it.message ?: "Database error")
            }
    }


    override fun forgetPassword(
        email: String,
        callback: (Boolean, String) -> Unit
    ) {
        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                callback(true, "Reset email sent")
            }
            .addOnFailureListener {
                callback(false, it.message ?: "Failed to send email")
            }
    }
}
