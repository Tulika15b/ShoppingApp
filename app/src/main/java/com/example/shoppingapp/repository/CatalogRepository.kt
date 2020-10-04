package com.example.shoppingapp.repository

import com.example.shoppingapp.data.CatalogDao
import com.example.shoppingapp.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogRepository @Inject constructor(private val catalogDao: CatalogDao) {

    fun getCategories() = catalogDao.fetchCategories()

    fun getProducts() = catalogDao.fetchProducts()

    fun getProductById(id : String) = catalogDao.getProductById(id)

    fun getProductsCategoryWise(id : String) = catalogDao.getProductByCategoryId(id)

    fun insertAllProducts(products : List<Product>) = catalogDao.insertAllProducts(products)

}