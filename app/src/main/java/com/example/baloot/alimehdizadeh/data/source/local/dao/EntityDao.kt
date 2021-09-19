package com.example.baloot.alimehdizadeh.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.baloot.alimehdizadeh.domain.model.local.DatabaseEntity

@Dao
abstract class EntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEntity(tracks: DatabaseEntity)

    @Query("select * from entity")
    abstract fun getAllEntity(): DatabaseEntity
}