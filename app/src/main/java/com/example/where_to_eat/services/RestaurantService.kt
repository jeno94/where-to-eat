package com.example.where_to_eat.services

import com.example.where_to_eat.models.Restaurants
import retrofit2.Call
import retrofit2.http.GET

interface RestaurantsService {

//    @GET("restaurants?city=Chicago")
    @GET("restaurants?city=Vail")
    fun getRestaurantList () : Call<Restaurants>

}