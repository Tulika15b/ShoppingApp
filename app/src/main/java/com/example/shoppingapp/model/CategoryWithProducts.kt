package com.example.shoppingapp.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithProducts (

    @Embedded val category : Category,


    val product : List<Product>
)