package com.example.shoppingapp.util

import androidx.room.TypeConverter
import com.example.shoppingapp.model.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class DataConverter {
    @TypeConverter
    fun fromCategoryProdList(categories: List<Category?>?): String? {
        if (categories == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Category?>?>() {}.type
        return gson.toJson(categories, type)
    }

    @TypeConverter
    fun toCategoryProdList(categoriesString: String?): List<Category>? {
        if (categoriesString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Category?>?>() {}.type
        return gson.fromJson<List<Category>>(categoriesString, type)
    }
}