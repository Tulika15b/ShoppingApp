package com.example.shoppingapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.shoppingapp.data.AppRoomDatabase
import com.example.shoppingapp.data.CatalogDao
import com.example.shoppingapp.data.CategoryCatalogResponse
import com.example.shoppingapp.data.ProductCatalogResponse
import com.example.shoppingapp.model.*
import com.example.shoppingapp.repository.CartRepository
import com.example.shoppingapp.repository.CatalogRepository
import com.example.shoppingapp.service.CatalogService
import com.example.shoppingapp.service.CatalogWebService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class CatalogViewModel (application: Application) : AndroidViewModel(application) {

     lateinit var repository : CatalogRepository

     init{
         val catalogDao = AppRoomDatabase.getDatabase(application).catalogDao()
         repository = CatalogRepository(catalogDao)
     }

    fun insertToDbCategories(){

    }

    val allCategories: MutableLiveData<List<Category>> = MutableLiveData()
    val allProducts : MutableLiveData<List<Product>> = MutableLiveData()
     val allProductsByCategory : MutableLiveData<List<Product>> = MutableLiveData()

    fun fetchCategories() : MutableLiveData<List<Category>>?{

        val request = CatalogService.buildService(CatalogWebService::class.java)
        val retrofitCall = request.fetchCategories()

        retrofitCall.enqueue(object : Callback<CategoryCatalogResponse> {
            override fun onFailure(call: Call<CategoryCatalogResponse>, t: Throwable) {
                Log.d("CVM", "Failed to fetch category data")
                allCategories.value
            }
            override fun onResponse(call: Call<CategoryCatalogResponse>, response: Response<CategoryCatalogResponse>) {
                if(response.isSuccessful){
                    allCategories.value = response.body()?.results
                    repository.insertAllCategories(allCategories.value!!)
                }
            }
        })
        return allCategories
    }

    fun fetchProducts(id : String) : MutableLiveData<List<Product>>?{

        val request = CatalogService.buildService(CatalogWebService::class.java)
        val retrofitCall = request.fetchProducts()

        retrofitCall.enqueue(object : Callback<ProductCatalogResponse> {
            override fun onFailure(call: Call<ProductCatalogResponse>, t: Throwable) {
                Log.d("CVM", "Failed to fetch product data")
            }
            override fun onResponse(call: Call<ProductCatalogResponse>, response: Response<ProductCatalogResponse>) {
                if(response.isSuccessful){

                    allProducts.value = response.body()?.results
                    repository.insertAllProducts(allProducts.value!!)

                    //allProductsByCategory.value = repository.getProductsCategoryWise(id).value!!

                }
            }
        })

        /*for(product in allProducts.value!!){
            //SELECT * FROM products
            //WHERE parentCategorylist LIKE '%id":14%'
            var matchStr = "%id\":14%"
            allProductsByCategory.value = repository.getProductsCategoryWise(matchStr)

        }*/
        //return allProductsByCategory

        return allProducts
    }

     fun getProductById(id : String) : Product{
        return repository.getProductById(id)
     }

     fun getProductbyCategory(idStr : String) : List<Product>{
         return repository.getProductsCategoryWise(idStr)
     }

     fun getProductWithCategories() : List<ProductWithCategories>{
         return repository.getProductWithCategories()
     }

     fun getCategoryWithProducts() : List<CategoryWithProducts>{
         return repository.getCategoryWithProducts()
     }


}