package com.example.smartshop.data.model

import com.example.smartshop.data.local.ProductEntity

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        name = name,
        quantity = quantity,
        price = price,
        imageUrl = imageUrl
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        quantity = quantity,
        price = price,
        imageUrl = imageUrl
    )
}
