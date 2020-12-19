package com.example.where_to_eat.models

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FavRestaurants::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favRestaurantsDao(): FavRestaurantsDao
}