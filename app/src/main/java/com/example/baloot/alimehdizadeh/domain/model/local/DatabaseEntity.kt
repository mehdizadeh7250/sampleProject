package com.example.baloot.alimehdizadeh.domain.model.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import kotlinx.parcelize.Parcelize

@Entity(tableName = "entity")
@Parcelize
@JsonObject
class DatabaseEntity(
    @PrimaryKey(autoGenerate = true)
    @JsonField(name = ["id"])
    var id: Int? = null,

    @ColumnInfo
    @JsonField(name = ["response"])
    var response: String? = null
) : Parcelable