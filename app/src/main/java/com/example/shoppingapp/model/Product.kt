package com.example.shoppingapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shoppingapp.util.DataConverter
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    @NonNull
    @field:SerializedName("id") val prodId: Int,

    @field:SerializedName("name") val prodName: String,

    @field:SerializedName("description") val prodDescr: String,

    @field:SerializedName("status") val prodStatus: Boolean,

    @field:SerializedName("image") val prodImageUrl: String,

    @field:SerializedName("price") val prodPrice: String,

    @field:SerializedName("discount_amount") val prodDiscount: String,

    @TypeConverters(DataConverter::class)

    @field:SerializedName("categories")
    val parentCategorylist: List<Category>?
):Serializable