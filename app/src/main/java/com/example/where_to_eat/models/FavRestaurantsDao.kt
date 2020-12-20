package com.example.where_to_eat.models

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete

@Dao
interface FavRestaurantsDao {
    @Query("SELECT * FROM favRestaurants")
    fun getAll(): List<FavRestaurants>

    @Insert
    fun insertAll(vararg favRes: FavRestaurants)

    @Delete
    fun delete(favRe: FavRestaurants)
}