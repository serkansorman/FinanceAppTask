package com.example.ccfinancegrouptask.di

import com.example.ccfinancegrouptask.common.Constants
import com.example.ccfinancegrouptask.data.remote.datasource.StockRemoteDataSource
import com.example.ccfinancegrouptask.data.remote.datasource.StockRemoteDataSourceImpl
import com.example.ccfinancegrouptask.data.repository.StockRepositoryImpl
import com.example.ccfinancegrouptask.domain.mapper.StockDescriptionUIMapper
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import com.example.ccfinancegrouptask.domain.usecase.GetStockDescriptionUseCase
import com.example.ccfinancegrouptask.domain.usecase.GetStockListFlowUseCase
import com.example.ccfinancegrouptask.domain.usecase.GetStockListSingleUseCase
import com.example.ccfinancegrouptask.ui.stockdescription.viewmodel.StockListViewModel
import com.example.ccfinancegrouptask.ui.stocklist.viewmodel.StockDescriptionViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { StockListViewModel(get(), get()) }
    viewModel { StockDescriptionViewModel(get()) }
}

val networkModule = module {

    fun provideHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        builder.addInterceptor { chain: Interceptor.Chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader(Constants.API_HEADER_KEY, "f0b4e70161msh3b3116511911dbbp181a18jsnf8e3cc9d7450")
                .addHeader(Constants.API_HEADER_HOST, "yh-finance.p.rapidapi.com").build()
            chain.proceed(request)
        }

        return builder.build()
    }


    single { provideHttpClient() }

}

val useCaseModule = module {
    single { GetStockListSingleUseCase(get()) }
    single { GetStockListFlowUseCase(get()) }
    single { GetStockDescriptionUseCase(get(), StockDescriptionUIMapper()) }

}

val repositoryModule = module {

    fun provideStockRepository(service: StockRemoteDataSource): StockRepository =
        StockRepositoryImpl(service)

    single { provideStockRepository(get()) }

}


val dataSourceModule = module {

    fun provideStockRemoteDataSource(client: OkHttpClient): StockRemoteDataSource =
        StockRemoteDataSourceImpl(client)

    single { provideStockRemoteDataSource(get()) }
}

