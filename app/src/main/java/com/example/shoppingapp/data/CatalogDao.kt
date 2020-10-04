package com.example.shoppingapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppingapp.model.Category
import com.example.shoppingapp.model.CategoryWithProducts
import com.example.shoppingapp.model.Product
import com.example.shoppingapp.model.ProductWithCategories

@Dao
interface CatalogDao {

    @Query("SELECT * FROM categories ORDER BY categoryName")
    fun fetchCategories(): LiveData<List<Category>>

    @Query("SELECT * FROM categories WHERE categoryId = :Id")
    fun getCategoryById(Id: String): LiveData<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategories(categories: List<Category>)



    //For Product Table

    @Query("SELECT * FROM products ORDER BY prodName")
    fun fetchProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE parentCategorylist LIKE :matchStr")
    fun getProductByCategoryId(matchStr: String): List<Product>

    @Query("SELECT * FROM products WHERE prodId = :Id")
    fun getProductById(Id: String): Product

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAllProducts(products: List<Product>)



    @Transaction
    @Query("SELECT * FROM categories")
    fun getCategoryWithProducts(): List<CategoryWithProducts>

    @Transaction
    @Query("SELECT * FROM products")
    fun getProductWithCategories(): List<ProductWithCategories>

}