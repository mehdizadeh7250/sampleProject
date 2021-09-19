package com.example.baloot.alimehdizadeh.di

import com.example.baloot.alimehdizadeh.data.repository.ErrorHandlerImpl
import com.example.baloot.alimehdizadeh.domain.repository.ErrorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorHandlerModule {

    @Binds
    abstract fun bindErrorHandler(
        errorHandlerImpl: ErrorHandlerImpl
    ): ErrorHandler
}