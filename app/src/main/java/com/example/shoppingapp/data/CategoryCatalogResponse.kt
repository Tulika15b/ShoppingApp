package com.example.shoppingapp.data

import com.example.shoppingapp.model.Category
import com.example.shoppingapp.model.Product
import com.google.gson.annotations.SerializedName

data class CategoryCatalogResponse (
    @field:SerializedName("data") val results: List<Category>
)