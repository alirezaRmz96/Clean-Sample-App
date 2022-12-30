package com.example.cleansample.data.repository.local.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        UserEntity::class
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun appDao(): AppDao
}