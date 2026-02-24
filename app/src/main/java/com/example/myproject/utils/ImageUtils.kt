package com.example.myproject.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

object ImageUtils {

    private val cloudinary = Cloudinary(
        mapOf(
            "cloud_name" to "diojj4owi",
            "api_key" to "375429816468252",
            "api_secret" to "M-Ve5TODTA5D4tTgttfKWcerWl0"
        )
    )

    suspend fun uploadImage(context: Context, imageUri: Uri): String? = withContext(Dispatchers.IO) {
        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri)
            var fileName = getFileNameFromUri(context, imageUri)
            fileName = fileName?.substringBeforeLast(".") ?: "uploaded_image"

            val response = cloudinary.uploader().upload(
                inputStream,
                ObjectUtils.asMap(
                    "public_id", fileName,
                    "resource_type", "image"
                )
            )

            var imageUrl = response["url"] as? String
            imageUrl?.replace("http://", "https://")
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun getFileNameFromUri(context: Context, uri: Uri): String? {
        var fileName: String? = null
        val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (nameIndex != -1) {
                    fileName = it.getString(nameIndex)
                }
            }
        }
        return fileName
    }
}