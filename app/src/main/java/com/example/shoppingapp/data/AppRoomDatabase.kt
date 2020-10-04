package com.example.shoppingapp.data

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shoppingapp.model.CartProductModel
import com.example.shoppingapp.model.Category
import com.example.shoppingapp.model.Product
import com.example.shoppingapp.util.DataConverter

@Database(entities = [Category::class, Product::class, CartProductModel::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun catalogDao(): CatalogDao
    abstract fun cartDao() : CartDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        //TODO::
        fun getDatabase(context: Context): AppRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "shopping_db"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}