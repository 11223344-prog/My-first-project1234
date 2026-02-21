package com.example.myproject.model

class InquiryModel(
    var inquiryId: String = "",
    var productId: String = "",
    var userId: String = "",
    var message: String = "",
    var timestamp: Long = System.currentTimeMillis()
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "inquiryId" to inquiryId,
            "productId" to productId,
            "userId" to userId,
            "message" to message,
            "timestamp" to timestamp
        )
    }
}