package com.example.shoppingapp.di

import com.example.shoppingapp.service.IProductCatalogService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideCatalogService(): IProductCatalogService {
        return IProductCatalogService.create()
    }
}