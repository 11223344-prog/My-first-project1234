package com.example.myproject.repository

import com.example.myproject.model.InquiryModel

class InquiryRepoImpl : InquiryRepo {

    // Temporary in-memory storage for testing
    private val inquiries = mutableListOf<InquiryModel>()

    // Add a new inquiry
    override fun addInquiry(
        inquiry: InquiryModel,
        callback: (Boolean, String) -> Unit
    ) {
        try {
            inquiries.add(inquiry)
            callback(true, "Inquiry added successfully")
        } catch (e: Exception) {
            callback(false, e.localizedMessage ?: "Failed to add inquiry")
        }
    }

    // Get all inquiries (optional filter by productId or userId)
    override fun getAllInquiries(
        productId: String?,
        userId: String?,
        callback: (Boolean, String, List<InquiryModel>) -> Unit
    ) {
        try {
            val filtered = inquiries.filter { inquiry ->
                (productId == null || inquiry.productId == productId) &&
                        (userId == null || inquiry.userId == userId)
            }
            callback(true, "Inquiries fetched successfully", filtered)
        } catch (e: Exception) {
            callback(false, e.localizedMessage ?: "Failed to fetch inquiries", emptyList())
        }
    }

    // Delete an inquiry by ID
    override fun deleteInquiry(
        inquiryId: String,
        callback: (Boolean, String) -> Unit
    ) {
        try {
            val removed = inquiries.removeAll { it.inquiryId == inquiryId }
            if (removed) {
                callback(true, "Inquiry deleted successfully")
            } else {
                callback(false, "Inquiry not found")
            }
        } catch (e: Exception) {
            callback(false, e.localizedMessage ?: "Failed to delete inquiry")
        }
    }
}