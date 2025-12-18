package com.example.smartshop.data.remote

import com.example.smartshop.data.local.ProductDao
import com.example.smartshop.data.model.Product
import com.example.smartshop.data.model.toDomain
import com.example.smartshop.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

class ProductRepository(
    private val dao: ProductDao,
    private val firestoreService: FirestoreProductService
) {

    // ---------- LOCAL ----------
    fun getProducts(): Flow<List<Product>> =
        dao.getAllProducts().map { list ->
            list.map { it.toDomain() }
        }

    suspend fun addProduct(name: String, quantity: Int, price: Double) {
        require(price > 0) { "Price must be > 0" }
        require(quantity >= 0) { "Quantity must be >= 0" }

        val product = Product(
            id = UUID.randomUUID().toString(),
            name = name,
            quantity = quantity,
            price = price
        )

        dao.insertProduct(product.toEntity())
        firestoreService.addOrUpdateProduct(product)
    }

    suspend fun updateProduct(product: Product) {
        dao.updateProduct(product.toEntity())
        firestoreService.addOrUpdateProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        dao.deleteProduct(product.toEntity())
        firestoreService.deleteProduct(product.id)
    }

    // ---------- SYNC CLOUD → LOCAL ----------
    fun syncFromCloud() {
        firestoreService.listenProducts { cloudProducts ->
            cloudProducts.forEach { product ->
                kotlinx.coroutines.GlobalScope.launch {
                    dao.insertProduct(product.toEntity())
                }
            }
        }
    }
}
