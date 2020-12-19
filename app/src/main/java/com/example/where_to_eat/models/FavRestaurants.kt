package com.example.where_to_eat.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavRestaurants (
    @PrimaryKey val restaurant_id: Int
)