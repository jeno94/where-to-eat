package com.example.where_to_eat.models

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import androidx.room.Room
import com.example.where_to_eat.R
import com.example.where_to_eat.helpers.DatabaseTest
import com.example.where_to_eat.ui.listing.ListingFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecyclerAdapter (var mcontext:Context,  private val list: ArrayList<Restaurants.Restaurant>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    val db = DatabaseTest.getDb(mcontext)
    lateinit var favList: List<FavRestaurants>

    init {
        GlobalScope.launch {
            favList = db.favRestaurantsDao().getAll()
            favList.forEach(){
                Log.d("DBBBBB ", "list element  ---->   $it")
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemName: TextView
        var itemAddress: TextView
        var itemPrice: TextView
        var itemImage: ImageView
        var btn: Button
        init {
            itemName = itemView.findViewById(R.id.restName)
            itemAddress = itemView.findViewById(R.id.restAddress)
            itemPrice = itemView.findViewById(R.id.restPrice)
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

             btn = itemView.findViewById(R.id.favBtn)

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.restaurant_card, viewGroup, false)


        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemName.text = list[i].name
        viewHolder.itemAddress.text = list[i].address
        viewHolder.itemPrice.text = list[i].price.toString()
        viewHolder.itemImage.setImageBitmap(getBitMapFromURL(list[i].image_url))


        val item = list[i]

        viewHolder.btn.setOnClickListener(){

            GlobalScope.launch {
//              Save the restaurant ID into the DB,
                db.favRestaurantsDao().insertAll(FavRestaurants(item.id))


            }
        }


//        val item = list.get(viewHolder.absoluteAdapterPosition)
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



