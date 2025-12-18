package com.example.smartshop.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartshop.data.model.Product
import com.example.smartshop.data.remote.ProductRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    // ---------- UI STATE ----------
    val products: StateFlow<List<Product>> =
        repository.getProducts()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    init {
        // Start cloud sync
        repository.syncFromCloud()
    }

    // ---------- ACTIONS ----------
    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            repository.deleteProduct(product)
        }
    }

    fun addProduct(
        name: String,
        quantity: Int,
        price: Double
    ) {
        viewModelScope.launch {
            repository.addProduct(name, quantity, price)
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            repository.updateProduct(product)
        }
    }
}
