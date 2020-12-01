package com.example.where_to_eat.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.where_to_eat.R
import com.example.where_to_eat.helpers.RestaurantsAdapter
import com.example.where_to_eat.models.Restaurants
import com.example.where_to_eat.services.RestaurantsService
import com.example.where_to_eat.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadRestaurants()

    }

    private fun loadRestaurants() {
        //initiate the service
        val destinationService  = ServiceBuilder.buildService(RestaurantsService::class.java)
        val requestCall =destinationService.getRestaurantList()
        //make network call asynchronously
        requestCall.enqueue(object : Callback<List<Restaurants>>{
            override fun onResponse(call: Call<List<Restaurants>>, response: Response<List<Restaurants>>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    val countryList  = response.body()!!
                    Log.d("Response", "countrylist size : ${countryList.size}")
                    country_recycler.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@MainActivity,2)
                        adapter = RestaurantsAdapter(response.body()!!)
                    }
                }else{
                    Toast.makeText(this@MainActivity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Restaurants>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong $t", Toast.LENGTH_SHORT).show()
            }
        })
    }
}