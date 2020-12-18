package com.example.where_to_eat.models

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.where_to_eat.R
import com.example.where_to_eat.ui.listing.ListingFragment
import com.squareup.picasso.Picasso
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class RecyclerAdapter (var mcontext:Context,  private val list: ArrayList<Restaurants.Restaurant>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemName: TextView
        var itemAddress: TextView
        var itemPrice: TextView
        var itemImage: ImageView

        init {
            itemName = itemView.findViewById(R.id.restName)
            itemAddress = itemView.findViewById(R.id.restPrice)
            itemPrice = itemView.findViewById(R.id.restAddress)
            itemImage = itemView.findViewById(R.id.restImage)

            itemView.setOnClickListener {
                var position: Int = getAdapterPosition()
                val context = itemView.context
                val intent = Intent(context, ListingFragment::class.java).apply {
                    putExtra("NUMBER", position)
                    putExtra("CODE", itemName.text)
                    putExtra("CATEGORY", itemAddress.text)
                    putExtra("CONTENT", itemPrice.text)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.restaurant_card, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemName.text = list[i].name
        viewHolder.itemAddress.text = list[i].phone
        viewHolder.itemPrice.text = list[i].state
        viewHolder.itemImage.setImageBitmap(getBitMapFromURL(list[i].image_url))

        val item = list.get(viewHolder.absoluteAdapterPosition)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getBitMapFromURL(imageURL: String): Bitmap? {
        var image: Bitmap? = null
        try {
            val `in` = java.net.URL(imageURL).openStream()
            image = BitmapFactory.decodeStream(`in`)
        }
        catch (e: Exception) {
            Log.e("Error Message", e.message.toString())
            e.printStackTrace()
        }
        return image
    }
}
