package com.example.myproject.model



data class ProjectModel(
    var productId: String="",
    var productName: String="",
    var productPrice: Double=0.0,
    var categoryId: String=""
){
    fun toMap(): Map<String,Any?>{
        return mapOf(
            "productId" to productId,
            "productName" to productName,
            "Price" to productPrice,
            "category" to categoryId
        )
    }
}