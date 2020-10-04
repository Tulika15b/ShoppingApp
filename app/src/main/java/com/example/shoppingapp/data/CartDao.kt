package com.example.shoppingapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppingapp.model.CartProductModel
import com.example.shoppingapp.model.Product

@Dao
interface CartDao {

    @Insert
    fun insertProduct(cartItem : CartProductModel)

    @Query("SELECT SUM(cart_prod_price*cart_prod_qty) FROM CartProductModel")
    fun getTotalCartValue(): Int

    @Query("SELECT uID,cart_prod_id, cart_prod_img_url,cart_prod_name, SUM(cart_prod_qty) as cart_prod_qty, SUM(cart_prod_price) as cart_prod_price FROM CartProductModel GROUP BY cart_prod_id")
    fun groupCartItems() : List<CartProductModel>
}