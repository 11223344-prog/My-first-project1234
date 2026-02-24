package com.example.myproject.model

data class ProductModel(
    var productId: String = "",
    var productName: String = "",
    var productPrice: Double = 0.0,
    var categoryId: String = "",
    var productImage: String = ""
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "productId" to productId,
            "productName" to productName,
            "productPrice" to productPrice,
            "categoryId" to categoryId,
            "productImage" to productImage
        )
    }
}