package com.example.where_to_eat.ui.listing

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.where_to_eat.models.Restaurants
import com.example.where_to_eat.services.RestaurantsService
import com.example.where_to_eat.R
import com.example.where_to_eat.models.RecyclerAdapter
import com.example.wheretoeat.ui.listing.ListingViewModel
import kotlinx.android.synthetic.main.fragment_listing.*
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.restaurant_card.view.*

class ListingFragment : Fragment(), View.OnClickListener {

    private lateinit var listingViewModel: ListingViewModel
    private lateinit var restaurants: ArrayList<Restaurants.Restaurant>
    private lateinit var restaurantsToList: ArrayList<Restaurants.Restaurant>

    private lateinit var linearLayoutManager: LinearLayoutManager


    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null


//    override fun onCreate(savedInstanceState: Bundle?) {
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        restaurants = getRestaurants()
//        restaurantsToList = restaurants

//        val view: View = inflater.inflate(R.layout.fragment_listing, container, false)
        val view: View = inflater!!.inflate(R.layout.fragment_listing, container, false)
//        val btn: Button = view.findViewById(R.id.favBtn)
//        btn.setOnClickListener(this)


//        return inflater.inflate(R.layout.fragment_listing, container, false)

        return view
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            restaurants = getRestaurants()
            restaurantsToList = restaurants
            adapter = RecyclerAdapter(this@ListingFragment.requireContext() , restaurantsToList) //not good
//            adapter = RecyclerAdapter(this@ListingFragment.requireContext() , getRestaurants())

        }
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
////        inflater.inflate(R.menu.top_navigation, menu)
//        inflater.inflate(R.menu.top_navigation, menu)
//        val searchItem = menu.findItem(R.id.menu_search)
//
//        if ( searchItem != null ){
//            val searchView = searchItem.actionView as SearchView
//
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    return true
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//
//                    if (newText!!.isNotEmpty()){
//                        restaurantsToList.clear()
//
//                        val searchText = newText.toLowerCase()
//                        restaurants.forEach{
//                            if (it.name.toLowerCase().contains(searchText)){
//                                restaurantsToList.add(it)
//                            }
//
//                        }
//                        adapter?.notifyDataSetChanged()
//                    }else{
//                        restaurantsToList.clear()
//                        restaurantsToList.addAll(restaurants)
//                        adapter?.notifyDataSetChanged()
//                    }
//                    return true
//                }
//
//            })
//        }

//        super.onCreateOptionsMenu(menu, inflater)
//    }


    fun getRestaurantsListAPI(): ArrayList<Restaurants.Restaurant> {
        return restaurants
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.favBtn -> {

            }
            else -> {
            }
        }
    }
    companion object {
        fun newInstance(): ListingFragment {
            return ListingFragment()
        }
    }

    private fun getRestaurants(): ArrayList<Restaurants.Restaurant> {

        val service = Retrofit.Builder()
            .baseUrl("https://ratpark-api.imok.space/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(RestaurantsService::class.java)

        val requestCall = service.getRestaurantList()
        var restaurantList = arrayListOf<Restaurants.Restaurant>()

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


        val resp:Response<Restaurants> =  requestCall.execute()
        restaurantList.addAll(resp.body()!!.restaurants!!)
//        Thread.sleep(3000)
        return restaurantList
    }
}