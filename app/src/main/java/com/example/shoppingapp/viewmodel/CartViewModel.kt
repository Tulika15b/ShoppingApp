package com.example.shoppingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppingapp.data.AppRoomDatabase
import com.example.shoppingapp.model.CartProductModel
import com.example.shoppingapp.model.Category
import com.example.shoppingapp.model.Product
import com.example.shoppingapp.repository.CartRepository

class CartViewModel (application: Application) : AndroidViewModel(application){


    lateinit var repository : CartRepository
    val allCartItems: MutableLiveData<List<CartProductModel>> = MutableLiveData()

    init{
        val cartDao = AppRoomDatabase.getDatabase(application).cartDao()
        repository = CartRepository(cartDao)
    }

    suspend fun getAllCartItems() : List<CartProductModel>{
        return repository.getAllCartProducts()
    }

     suspend fun insertToCart(product: Product, qty : Int){
        var item : CartProductModel = CartProductModel(product.prodName, product.prodPrice, qty, product.prodImageUrl)
        repository.insertProduct(item)
    }

    suspend fun getTotalValue() : Int{
        return repository.getTotalCartValue()
    }
}