package com.example.myproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myproject.model.ProductModel
import com.example.myproject.repository.ProductRepo

class ProductViewModel(
    private val repo: ProductRepo
) : ViewModel() {

    private val _products = MutableLiveData<List<ProductModel>>()
    val products: LiveData<List<ProductModel>> = _products

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message


    // ADD PRODUCT
    fun addProduct(model: ProductModel) {
        repo.addProduct(model) { success, msg ->
            _message.postValue(msg)
            if (success) {
                getAllProducts()
            }
        }
    }

    // DELETE PRODUCT
    fun deleteProduct(productId: String) {
        repo.deleteProduct(productId) { success, msg ->
            _message.postValue(msg)
            if (success) {
                getAllProducts()
            }
        }
    }

    // UPDATE PRODUCT
    fun updateProduct(model: ProductModel) {
        repo.updateProduct(model) { success, msg ->
            _message.postValue(msg)
            if (success) {
                getAllProducts()
            }
        }
    }

    // GET SINGLE PRODUCT
    fun getProductById(productId: String) {
        repo.getProductById(productId) { success, msg, product ->
            _message.postValue(msg)
        }
    }

    // GET ALL PRODUCTS
    fun getAllProducts() {
        repo.getAllProducts { success, msg, list ->
            _message.postValue(msg)
            if (success) {
                _products.postValue(list)
            }
        }
    }

    // GET BY CATEGORY
    fun getProductsByCategory(categoryId: String) {
        repo.getProductsByCategory(categoryId) { success, msg, list ->
            _message.postValue(msg)
            if (success) {
                _products.postValue(list)
            }
        }
    }
}