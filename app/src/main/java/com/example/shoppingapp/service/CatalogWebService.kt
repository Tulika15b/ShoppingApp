package com.example.shoppingapp.service

import com.example.shoppingapp.data.CategoryCatalogResponse
import com.example.shoppingapp.data.ProductCatalogResponse
import com.example.shoppingapp.model.Category
import com.example.shoppingapp.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatalogWebService {

    @GET("public-api/categories")
    fun fetchCategories(): Call<CategoryCatalogResponse>


    @GET("public-api/products")
    fun fetchProducts(): Call<ProductCatalogResponse>
}