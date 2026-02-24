package com.example.myproject.model

data class InquiryModel(
    var inquiryId: String = "",
    var productId: String = "",
    var userId: String = "",
    var message: String = "",
    var inquiryImage: String = "",
    var inquiryStatus: String = "In-Progress"
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "inquiryId" to inquiryId,
            "productId" to productId,
            "userId" to userId,
            "message" to message,
            "inquiryImage" to inquiryImage,
            "inquiryStatus" to inquiryStatus
        )
    }
}