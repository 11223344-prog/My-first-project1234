package com.example.myproject.repository

import com.example.myproject.model.InquiryModel

interface InquiryRepo {

    // Add a new inquiry
    fun addInquiry(
        inquiry: InquiryModel,
        callback: (success: Boolean, message: String) -> Unit
    )

    // Get all inquiries (optionally filtered by productId or userId)
    fun getAllInquiries(
        productId: String? = null,
        userId: String? = null,
        callback: (success: Boolean, message: String, list: List<InquiryModel>) -> Unit
    )

    // Delete an inquiry
    fun deleteInquiry(
        inquiryId: String,
        callback: (success: Boolean, message: String) -> Unit
    )
}