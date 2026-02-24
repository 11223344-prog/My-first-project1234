package com.example.myproject.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myproject.model.ProductModel
import com.example.myproject.repository.ProductRepo

class ProductViewModel(
    private val repo: ProductRepo
) : ViewModel() {

    private val _products = MutableLiveData<List<ProductModel?>>()
    val products: LiveData<List<ProductModel?>> = _products

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    // Fetch all products on initialization
    init {
        getAllProducts()
    }

    fun addProduct(context: Context, model: ProductModel, imageUri: Uri?) {
        _loading.value = true
        repo.addProduct(context, model, imageUri) { success, msg ->
            _loading.postValue(false)
            _message.postValue(msg)
            if (success) getAllProducts()
        }
    }

    fun deleteProduct(productId: String) {
        // Creating a dummy model with the ID to match your Repo's deleteProduct(ProductModel)
        val dummyModel = ProductModel(productId = productId)
        _loading.value = true
        repo.deleteProduct(dummyModel) { success, msg ->
            _loading.postValue(false)
            _message.postValue(msg)
            if (success) getAllProducts()
        }
    }

    fun updateProduct(model: ProductModel) {
        _loading.value = true
        repo.updateProduct(model) { success, msg ->
            _loading.postValue(false)
            _message.postValue(msg)
            if (success) getAllProducts()
        }
    }

    fun getProductById(productId: String) {
        val dummyModel = ProductModel(productId = productId)
        repo.getProductById(dummyModel) { success, msg, product ->
            _message.postValue(msg)
            // You might want a separate LiveData for a single selected product
        }
    }

    fun getAllProducts() {
        _loading.value = true
        // Passing an empty model because your interface getAllProduct(model, callback) requires one
        repo.getAllProduct(ProductModel()) { success, msg, list ->
            _loading.postValue(false)
            if (success) {
                _products.postValue(list)
            } else {
                _message.postValue(msg)
            }
        }
    }

    fun getProductsByCategory(categoryId: String) {
        _loading.value = true
        val dummyModel = ProductModel(categoryId = categoryId)
        repo.getCategoryById(dummyModel) { success, msg, list ->
            _loading.postValue(false)
            if (success) {
                _products.postValue(list)
            } else {
                _message.postValue(msg)
            }
        }
    }
}