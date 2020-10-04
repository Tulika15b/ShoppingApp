package com.example.shoppingapp.di

import android.content.Context
import com.example.shoppingapp.data.AppRoomDatabase
import com.example.shoppingapp.data.CartDao
import com.example.shoppingapp.data.CatalogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DbModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppRoomDatabase {
        return AppRoomDatabase.getDatabase(context)
    }

    @Provides
    fun provideCatalogDao(appDatabase: AppRoomDatabase): CatalogDao {
        return appDatabase.catalogDao()
    }

    @Provides
    fun provideCartDao(appDatabase: AppRoomDatabase): CartDao {
        return appDatabase.cartDao()
    }

}