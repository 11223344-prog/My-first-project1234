package com.example.myproject.repository

import android.content.Context
import android.net.Uri

interface CommonRepo {
    suspend fun uploadImage(context: Context, imageUri: Uri): String?
}