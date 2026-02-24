package com.example.myproject.repository

import android.content.Context
import android.net.Uri
import com.example.myproject.model.ProductModel

interface ProductRepo {
    fun addProduct(context: Context, model: ProductModel, imageUri: Uri?, callback: (Boolean, String) -> Unit)
    fun deleteProduct(model: ProductModel, callback: (Boolean, String) -> Unit)
    fun updateProduct(model: ProductModel, callback: (Boolean, String) -> Unit)
    fun getProductById(model: ProductModel, callback: (Boolean, String, ProductModel) -> Unit)
    fun getAllProduct(model: ProductModel, callback: (Boolean, String, List<ProductModel?>) -> Unit)
    fun getCategoryById(model: ProductModel, callback: (Boolean, String, List<ProductModel?>) -> Unit)
}