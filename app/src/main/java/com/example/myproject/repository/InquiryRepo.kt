package com.example.myproject.repository

import com.example.myproject.model.InquiryModel

interface InquiryRepo {
    fun addInquiry(inquiry: InquiryModel, callback: (Boolean, String) -> Unit)
    fun getAllInquiries(
        productId: String?,
        userId: String?,
        callback: (Boolean, String, List<InquiryModel>) -> Unit
    )
    fun deleteInquiry(inquiryId: String, callback: (Boolean, String) -> Unit)
}