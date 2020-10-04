package com.example.shoppingapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppingapp.model.Category
import com.example.shoppingapp.model.Product

@Dao
interface CatalogDao {

    @Query("SELECT * FROM categories ORDER BY categoryName")
    fun fetchCategories(): LiveData<List<Category>>

    @Query("SELECT * FROM categories WHERE categoryId = :Id")
    fun getCategoryById(Id: String): LiveData<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCategories(categories: List<Category>)



    //For Product Table

    @Query("SELECT * FROM products ORDER BY prodName")
    fun fetchProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE parentCategorylist LIKE :matchStr")
    fun getProductByCategoryId(matchStr: String): List<Product>

    @Query("SELECT * FROM products WHERE prodId = :Id")
    fun getProductById(Id: String): Product

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAllProducts(products: List<Product>)


}