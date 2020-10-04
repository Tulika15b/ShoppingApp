package com.example.shoppingapp

import com.example.shoppingapp.model.Category
import com.example.shoppingapp.util.DataConverter
import org.junit.Assert
import org.junit.Test

class DataConverterTest {

    val testCategories = arrayListOf(
        Category(1, "Category1"),
        Category(2, "Category2")
    )


    @Test
    fun fromCategoryProdList(){
        var result : String? = DataConverter().fromCategoryProdList(testCategories)
        Assert.assertEquals( DataConverter().toCategoryProdList(result)?.get(0)?.categoryId, 1)
    }
}