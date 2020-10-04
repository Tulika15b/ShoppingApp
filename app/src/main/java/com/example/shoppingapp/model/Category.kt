package com.example.shoppingapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "categories")

data class Category(
    @PrimaryKey
    @field:SerializedName("id") val categoryId: Int,

    @field:SerializedName("name") val categoryName: String/*,
    @field:SerializedName("description") val categoryDescr : String,
    @field:SerializedName("status")val categoryStatus : Boolean*/
) : Serializable