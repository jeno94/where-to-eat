package com.example.where_to_eat.helpers

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.where_to_eat.R
import com.example.where_to_eat.models.Restaurants
//import com.squareup.picasso.Picasso

class RestaurantsAdapter (private val restaurantsList: List<Restaurants>) : RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_element,parent,false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return restaurantsList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${restaurantsList.size} ")


//        return holder.bind(restaurantsList[position])

    }
    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {


//        var imageView = itemView.findViewById<ImageView>(R.id.ivFlag)
//        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
//        var tvCases = itemView.findViewById<TextView>(R.id.tvCases)

//        fun bind(country: Restaurants) {
//
////            val name ="Cases :${country.cases.toString()}"
////            tvTitle.text = country.country
////            tvCases.text = name
////            Picasso.get().load(country.countryInfo.flag).into(imageView)
//        }

    }
}