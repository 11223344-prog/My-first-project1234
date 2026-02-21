package com.example.myproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myproject.model.InquiryModel
import com.example.myproject.repository.InquiryRepo
import com.example.myproject.repository.InquiryRepoImpl

class InquiryViewModel(
    private val repo: InquiryRepo = InquiryRepoImpl()
) : ViewModel() {

    // LiveData for list of inquiries
    private val _inquiries = MutableLiveData<List<InquiryModel>>()
    val inquiries: LiveData<List<InquiryModel>> = _inquiries

    // LiveData for messages (success/error)
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    // ADD INQUIRY
    fun addInquiry(
        inquiry: InquiryModel,
        callback: (success: Boolean, message: String) -> Unit
    ) {
        repo.addInquiry(inquiry) { success, msg ->
            _message.postValue(msg)
            if (success) {
                getAllInquiries(inquiry.productId)
            }
            callback(success, msg)
        }
    }

    // GET ALL INQUIRIES (optional filter by productId or userId)
    fun getAllInquiries(
        productId: String? = null,
        userId: String? = null
    ) {
        repo.getAllInquiries(productId, userId) { success, msg, list ->
            _message.postValue(msg)
            if (success) {
                _inquiries.postValue(list)
            }
        }
    }

    // DELETE INQUIRY
    fun deleteInquiry(
        inquiryId: String,
        callback: (success: Boolean, message: String) -> Unit
    ) {
        repo.deleteInquiry(inquiryId) { success, msg ->
            _message.postValue(msg)
            if (success) {
                getAllInquiries() // Refresh list after deletion
            }
            callback(success, msg)
        }
    }
}