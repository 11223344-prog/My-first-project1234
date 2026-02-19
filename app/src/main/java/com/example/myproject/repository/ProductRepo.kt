package com.example.myproject.repository

import com.example.myproject.model.ProductModel

interface ProductRepo{
    fun addProduct(model: ProductModel, callback:(Boolean,String)->Unit)
    fun deleteProduct(model: ProductModel, callback: (Boolean, String) -> Unit)
    fun updateProduct(model: ProductModel, callback: (Boolean, String) -> Unit)
    fun getProductById(model: ProductModel, callback: (Boolean, String, ProductModel) -> Unit)
    fun getAllProduct(model: ProductModel, callback: (Boolean, String, List<ProductModel?>) -> Unit)
    fun getCategoryById(model: ProductModel, callback: (Boolean, String, List<ProductModel?>) -> Unit)
}