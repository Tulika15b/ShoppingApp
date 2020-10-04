package com.example.shoppingapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class CartProductModel (

    @ColumnInfo
    var cart_prod_id : Int,

    @ColumnInfo
    var cart_prod_name : String,

    @ColumnInfo
    var cart_prod_price : String,

    @ColumnInfo
    var cart_prod_qty : Int,

    @ColumnInfo
    var cart_prod_img_url : String


){
    @PrimaryKey(autoGenerate = true) var uID : Int = 0
}