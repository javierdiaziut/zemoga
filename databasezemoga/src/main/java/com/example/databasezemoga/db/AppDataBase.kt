package com.example.databasezemoga.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.databasezemoga.dao.PostDao
import com.example.databasezemoga.entity.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}