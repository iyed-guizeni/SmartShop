package com.example.smartshop.data.remote

import com.example.smartshop.data.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreProductService {

    private val firestore = FirebaseFirestore.getInstance()
    private val collection = firestore.collection("products")

    suspend fun addOrUpdateProduct(product: Product) {
        collection.document(product.id).set(product).await()
    }

    suspend fun deleteProduct(productId: String) {
        collection.document(productId).delete().await()
    }

    fun listenProducts(onChange: (List<Product>) -> Unit) {
        collection.addSnapshotListener { snapshot, _ ->
            if (snapshot != null) {
                val products = snapshot.toObjects(Product::class.java)
                onChange(products)
            }
        }
    }
}
