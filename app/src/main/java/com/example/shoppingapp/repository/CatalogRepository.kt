package com.example.shoppingapp.repository

import com.example.shoppingapp.data.CatalogDao
import com.example.shoppingapp.model.Category
import com.example.shoppingapp.model.Product
import com.example.shoppingapp.model.ProductWithCategories
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogRepository @Inject constructor(private val catalogDao: CatalogDao) {

    fun getCategories() = catalogDao.fetchCategories()

    fun getProducts() = catalogDao.fetchProducts()

    suspend fun getProductById(id : String) = catalogDao.getProductById(id)

    fun getProductsByCategoryId(id : Int) = catalogDao.getProductByCategoryId(id)

    suspend fun insertAllCategories(categories: List<Category>) = catalogDao.insertAllCategories(categories)

    fun insertAllProducts(products : List<Product>) = catalogDao.insertAllProducts(products)

    fun getCategoryWithProducts() = catalogDao.getCategoryWithProducts()

    fun getProductWithCategories() = catalogDao.getProductWithCategories()

}