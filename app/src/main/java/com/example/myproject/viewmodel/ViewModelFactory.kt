package com.example.myproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myproject.repository.InquiryRepoImpl
import com.example.myproject.repository.ProductRepoImpl

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(ProductRepoImpl()) as T
        }
        if (modelClass.isAssignableFrom(InquiryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InquiryViewModel(InquiryRepoImpl()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}