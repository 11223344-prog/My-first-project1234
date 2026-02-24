package com.example.myproject.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myproject.model.InquiryModel
import com.example.myproject.repository.InquiryRepo
import com.example.myproject.utils.ImageUtils
import kotlinx.coroutines.launch

class InquiryViewModel(private val repo: InquiryRepo) : ViewModel() {

    private val _inquiries = MutableLiveData<List<InquiryModel>>()
    val inquiries: LiveData<List<InquiryModel>> = _inquiries

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun addInquiry(context: Context, inquiry: InquiryModel, imageUri: Uri?) {
        _loading.value = true
        viewModelScope.launch {
            val imageUrl = imageUri?.let { ImageUtils.uploadImage(context, it) }
            inquiry.inquiryImage = imageUrl ?: ""
            repo.addInquiry(inquiry) { success, msg ->
                _loading.postValue(false)
                _message.postValue(msg)
                if (success) {
                    // Refresh inquiries after adding
                    getAllInquiries()
                }
            }
        }
    }

    fun getAllInquiries(productId: String? = null, userId: String? = null) {
        _loading.value = true
        repo.getAllInquiries(productId, userId) { success, msg, list ->
            _loading.postValue(false)
            if (success) {
                _inquiries.postValue(list)
            } else {
                _message.postValue(msg)
            }
        }
    }

    fun deleteInquiry(inquiryId: String) {
        _loading.value = true
        repo.deleteInquiry(inquiryId) { success, msg ->
            _loading.postValue(false)
            _message.postValue(msg)
            if (success) {
                // Refresh inquiries after deleting
                getAllInquiries()
            }
        }
    }
}