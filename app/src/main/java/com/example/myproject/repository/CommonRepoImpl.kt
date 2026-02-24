package com.example.myproject.repository

import android.content.Context
import android.net.Uri
import com.example.myproject.utils.ImageUtils

class CommonRepoImpl : CommonRepo {
    override suspend fun uploadImage(context: Context, imageUri: Uri): String? {
        return ImageUtils.uploadImage(context, imageUri)
    }
}