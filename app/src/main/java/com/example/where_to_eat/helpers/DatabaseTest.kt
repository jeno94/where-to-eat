package com.example.where_to_eat.helpers

import android.content.Context
import androidx.room.Room
import com.example.where_to_eat.models.AppDatabase

object DatabaseTest {

    fun getDb(context:Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "favourite-restaurants"
        ).build()
    }

}