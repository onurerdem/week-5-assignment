package com.merttoptas.retrofittutorial.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.merttoptas.retrofittutorial.utils.Constants

@Entity(tableName = Constants.TABLE_NAME_OF_USER)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long? = null,
    @ColumnInfo(name = "userId") val userId: String?,
    @ColumnInfo(name = "nameOfUser") val nameOfUser: String?,
)
