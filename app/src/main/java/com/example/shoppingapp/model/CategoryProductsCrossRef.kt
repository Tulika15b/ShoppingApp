package com.example.shoppingapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(tableName = "crossRef", primaryKeys = ["categoryId", "prodId"])
data class CategoryProductsCrossRef (
    val categoryId: Int,
    val prodId: String
)

data class CategoryWithProducts(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "prodId",
        associateBy = Junction(CategoryProductsCrossRef::class)
    )
    val products: List<Product>
)

data class ProductWithCategories(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "prodId",
        entityColumn = "categoryId",
        associateBy = Junction(CategoryProductsCrossRef::class)
    )
    val categories: List<Category>
)