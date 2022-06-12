package com.example.ccfinancegrouptask.di

import com.example.ccfinancegrouptask.data.remote.datasource.StockRemoteDataSource
import com.example.ccfinancegrouptask.data.repository.StockRepositoryImpl
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object StockRepositoryModule {

    @Provides
    @Singleton
    fun provideStockRepository(service: StockRemoteDataSource): StockRepository =
        StockRepositoryImpl(service)
}