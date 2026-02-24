package com.example.myproject.repository

import android.content.Context
import android.net.Uri
import com.example.myproject.model.ProductModel
import com.example.myproject.utils.ImageUtils
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductRepoImpl : ProductRepo {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("products")

    override fun addProduct(context: Context, model: ProductModel, imageUri: Uri?, callback: (Boolean, String) -> Unit) {
        val id = model.productId.ifEmpty { database.push().key ?: "" }
        model.productId = id

        if (imageUri != null) {
            CoroutineScope(Dispatchers.IO).launch {
                val imageUrl = ImageUtils.uploadImage(context, imageUri)
                if (imageUrl != null) {
                    model.productImage = imageUrl
                    database.child(id).setValue(model).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            callback(true, "Product added successfully")
                        } else {
                            callback(false, task.exception?.message ?: "Failed to add product")
                        }
                    }
                } else {
                    callback(false, "Image upload failed")
                }
            }
        } else {
            database.child(id).setValue(model).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Product added successfully")
                } else {
                    callback(false, task.exception?.message ?: "Failed to add product")
                }
            }
        }
    }

    override fun deleteProduct(model: ProductModel, callback: (Boolean, String) -> Unit) {
        database.child(model.productId).removeValue().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(true, "Product deleted successfully")
            } else {
                callback(false, task.exception?.message ?: "Failed to delete product")
            }
        }
    }

    override fun updateProduct(model: ProductModel, callback: (Boolean, String) -> Unit) {
        database.child(model.productId).updateChildren(model.toMap().filterValues { it != null } as Map<String, Any>)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, "Product updated successfully")
                } else {
                    callback(false, task.exception?.message ?: "Failed to update product")
                }
            }
    }

    override fun getProductById(model: ProductModel, callback: (Boolean, String, ProductModel) -> Unit) {
        database.child(model.productId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val product = snapshot.getValue(ProductModel::class.java)
                if (product != null) {
                    callback(true, "Product retrieved", product)
                } else {
                    callback(false, "Product not found", ProductModel())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                callback(false, error.message, ProductModel())
            }
        })
    }

    override fun getAllProduct(model: ProductModel, callback: (Boolean, String, List<ProductModel?>) -> Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productList = mutableListOf<ProductModel?>()
                for (data in snapshot.children) {
                    val product = data.getValue(ProductModel::class.java)
                    productList.add(product)
                }
                callback(true, "All products fetched", productList)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(false, error.message, emptyList())
            }
        })
    }

    override fun getCategoryById(model: ProductModel, callback: (Boolean, String, List<ProductModel?>) -> Unit) {
        database.orderByChild("categoryId").equalTo(model.categoryId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val filteredList = snapshot.children.map { it.getValue(ProductModel::class.java) }
                    callback(true, "Category products fetched", filteredList)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(false, error.message, emptyList())
                }
            })
    }
}