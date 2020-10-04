package com.example.shoppingapp.repository

import com.example.shoppingapp.data.CartDao
import com.example.shoppingapp.data.CatalogDao
import com.example.shoppingapp.model.CartProductModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor(private val cartDao: CartDao) {

    fun insertProduct(item : CartProductModel)  = cartDao.insertProduct(item)

    fun getTotalCartValue() = cartDao.getTotalCartValue()

    fun getAllCartProductsGrouped() = cartDao.groupCartItems()
}
