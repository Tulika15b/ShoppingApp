package com.example.shoppingapp.data

import com.example.shoppingapp.model.Product
import com.google.gson.annotations.SerializedName

data class ProductCatalogResponse (
    @field:SerializedName("data") val results: List<Product>
)