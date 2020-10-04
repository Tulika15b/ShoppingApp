package com.example.shoppingapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import com.example.shoppingapp.model.CartProductModel
import com.example.shoppingapp.model.Product
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.hamcrest.CoreMatchers.equalTo

class DaoTest {
    private lateinit var database: AppRoomDatabase
    private lateinit var catalogDao: CatalogDao
    private lateinit var cartDao : CartDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppRoomDatabase::class.java).build()
        catalogDao = database.catalogDao()
        cartDao = database.cartDao()

        val testProducts = arrayListOf(
            Product(1, "Prod1", "Apple", true, "https://lorempixel.com/250/250", "100", "10", null),
            Product(2, "Prod2", "Banana", true, "https://lorempixel.com/250/250", "100", "10", null),
            Product(3, "Prod3", "Camel", true, "https://lorempixel.com/250/250", "100", "10", null)
        )

        val product : CartProductModel = CartProductModel("Apple", "100", 1, "https://lorempixel.com/250/250")

        database.catalogDao().insertAllProducts(testProducts)
        database.cartDao().insertProduct(product)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun getAllCartProducts() = runBlocking {
        assertThat(cartDao.getAllCartProducts().get(0).cart_prod_name, equalTo("Apple"))
    }

    @Test
    fun getTotalCartValue() = runBlocking {
        assertThat(cartDao.getTotalCartValue(), equalTo(100))
    }

    @Test
    fun getAllProducts() = runBlocking {
        assertThat(catalogDao.fetchProducts().value?.size, equalTo(3))
    }
}