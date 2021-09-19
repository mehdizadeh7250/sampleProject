package com.example.baloot.alimehdizadeh.di

import android.content.Context
import com.example.baloot.alimehdizadeh.R
import com.example.baloot.alimehdizadeh.data.repository.GetDataQueryRepositoryImpl
import com.example.baloot.alimehdizadeh.data.source.local.AppDataBase
import com.example.baloot.alimehdizadeh.data.source.network.interceptor.HeaderInterceptor
import com.example.baloot.alimehdizadeh.data.source.network.routes.ApiService
import com.example.baloot.alimehdizadeh.domain.repository.ErrorHandler
import com.example.baloot.alimehdizadeh.domain.repository.GetDataQueryRepository
import com.example.baloot.alimehdizadeh.utils.FlowCallAdapterFactory
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.aurae.retrofit2.LoganSquareConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideHeaderInterceptor() = HeaderInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
            .addNetworkInterceptor(StethoInterceptor()).build()

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(context.getString(R.string.baseUrl))
            .client(client)
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .addConverterFactory(LoganSquareConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideServices(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideGetDataQuery(
        appDataBase: AppDataBase,
        apiService: ApiService,
        errorHandler: ErrorHandler
    ): GetDataQueryRepository = GetDataQueryRepositoryImpl(appDataBase, apiService, errorHandler)


}