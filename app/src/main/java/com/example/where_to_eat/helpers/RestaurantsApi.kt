package com.example.where_to_eat.helpers

import com.example.where_to_eat.models.Restaurants
import com.example.where_to_eat.models.Restaurants.*
import com.example.where_to_eat.services.RestaurantsService
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RestaurantsApi {
    private var restaurants: ArrayList<Restaurants.Restaurant>


    init{
        restaurants = getRestaurantsFromAPI()
    }

    fun getRestaurants(): ArrayList<Restaurants.Restaurant> {
        return restaurants
    }
    fun getRestaurantsFromAPI(): ArrayList<Restaurants.Restaurant> {

        val service = Retrofit.Builder()
            .baseUrl("https://ratpark-api.imok.space/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(RestaurantsService::class.java)

        val requestCall = service.getRestaurantList()
        var restaurantList = arrayListOf<Restaurant>()

//        requestCall.execute(object : Callback<Restaurants> {
//            val z= 0
//            override fun onResponse(call: Call<Restaurants>, response: Response<Restaurants>) {
//                Log.d("Response", "onResponse: ${response.body()}")
//                if (response.isSuccessful){
//                    restaurantList.addAll(response.body()!!.restaurants!!)
//                    val y= 0
//
//                    Log.d("Response", "country list size : ${restaurantsToList.size}")
//                }else{
////                    Toast.makeText(this@ListingFragment, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
//                    val y= 0
//
//                }
//            }
//            override fun onFailure(call: Call<Restaurants>, t: Throwable) {
//                val x= 0
//            }
//        })


        val resp: Response<Restaurants> =  requestCall.execute()
        restaurantList.addAll(resp.body()!!.restaurants!!)
//        Thread.sleep(3000)
        return restaurantList
    }
}