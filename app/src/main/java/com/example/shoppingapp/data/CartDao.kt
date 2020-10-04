package com.example.shoppingapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppingapp.model.CartProductModel
import com.example.shoppingapp.model.Product

@Dao
interface CartDao {

    @Insert
    fun insertProduct(cartItem : CartProductModel)

    @Query("SELECT * FROM CartProductModel")
    fun getAllCartProducts(): List<CartProductModel>

    @Query("SELECT SUM(cart_prod_price*cart_prod_qty) FROM CartProductModel")
    fun getTotalCartValue(): Int
}