package com.example.baloot.alimehdizadeh.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baloot.alimehdizadeh.data.source.local.dao.EntityDao
import com.example.baloot.alimehdizadeh.domain.model.local.DatabaseEntity

@Database(entities = [DatabaseEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getEntityDao(): EntityDao
}