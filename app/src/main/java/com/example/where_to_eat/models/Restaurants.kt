package com.example.where_to_eat.models

data class Restaurants(
//    val current_page: Int,
    val page: Int,
    val per_page: Int,
    val restaurants: List<Restaurant>,
    val total_entries: Int
) {
    data class Restaurant(
        val address: String,
        val area: String,
        val city: String,
        val country: String,
        val id: Int,
        val image_url: String,
        val lat: Double,
        val lng: Double,
        val mobile_reserve_url: String,
        val name: String,
        val phone: String,
        val postal_code: String,
        val price: Int,
        val reserve_url: String,
        val state: String
    )
}