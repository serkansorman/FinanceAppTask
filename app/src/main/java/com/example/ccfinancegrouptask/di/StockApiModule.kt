package com.example.ccfinancegrouptask.di

import com.example.ccfinancegrouptask.common.Constants
import com.example.ccfinancegrouptask.data.remote.datasource.StockRemoteDataSource
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StockApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.connectTimeout(5, TimeUnit.SECONDS)

        builder.addInterceptor { chain: Interceptor.Chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key", "6582e4337amsha2af643418a0ca3p17e2d0jsn857c61a5cfec")
                .addHeader("X-RapidAPI-Host", "yh-finance.p.rapidapi.com").build()
            chain.proceed(request)
        }

        val client = builder.build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }


    @Provides
    @Singleton
    fun provideStockService(retrofit: Retrofit): StockRemoteDataSource {
        return retrofit.create(StockRemoteDataSource::class.java)
    }

}