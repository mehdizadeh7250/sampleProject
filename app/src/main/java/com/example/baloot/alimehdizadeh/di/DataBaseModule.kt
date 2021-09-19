package com.example.baloot.alimehdizadeh.di

import android.content.Context
import androidx.room.Room
import com.example.baloot.alimehdizadeh.data.source.local.AppDataBase
import com.example.baloot.alimehdizadeh.data.source.local.dao.EntityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, "app-db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    internal fun provideEntityDao(appDatabase: AppDataBase): EntityDao {
        return appDatabase.getEntityDao()
    }
}