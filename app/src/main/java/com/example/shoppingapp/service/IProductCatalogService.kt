package com.example.shoppingapp.service

import androidx.lifecycle.LiveData
import com.example.shoppingapp.data.CategoryCatalogResponse
import com.example.shoppingapp.data.ProductCatalogResponse
import com.example.shoppingapp.model.Category
import com.example.shoppingapp.model.Product
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface IProductCatalogService {

    companion object {
        private const val BASE_URL = "https://gorest.co.in/"

        fun create(): IProductCatalogService {

            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IProductCatalogService::class.java)
        }
    }

    @GET("public-api/categories")
    fun fetchCategories(): Call<List<Category>>


    @GET("public-api/products")
    fun fetchProducts(): Call<List<Product>>

}