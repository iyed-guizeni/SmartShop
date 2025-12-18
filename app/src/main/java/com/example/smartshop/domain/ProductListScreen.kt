package com.example.smartshop.ui.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartshop.data.model.Product

@Composable
fun ProductListScreen(
    viewModel: ProductViewModel,
    onAddProductClick: () -> Unit,
    onEditProductClick: (Product) -> Unit
) {
    val products by viewModel.products.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddProductClick) {
                Text("+")
            }
        }
    ) { padding ->

        if (products.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No products available")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(products) { product ->
                    ProductItem(
                        product = product,
                        onDelete = { viewModel.deleteProduct(product) },
                        onEdit = { onEditProductClick(product) }
                    )
                }
            }
        }
    }
}
