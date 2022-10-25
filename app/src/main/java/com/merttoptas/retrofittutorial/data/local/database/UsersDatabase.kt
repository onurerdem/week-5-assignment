package com.merttoptas.retrofittutorial.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.merttoptas.retrofittutorial.data.local.database.converter.DaoConverter
import com.merttoptas.retrofittutorial.data.local.database.dao.UserDao
import com.merttoptas.retrofittutorial.data.local.database.entity.UserEntity
import com.merttoptas.retrofittutorial.utils.Constants

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(DaoConverter::class)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: UsersDatabase? = null

        fun getDatabase(context: Context): UsersDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, UsersDatabase::class.java, Constants.TABLE_USER)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

    }
}