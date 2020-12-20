package com.example.wheretoeat.ui.profile

import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.where_to_eat.R
import com.example.where_to_eat.helpers.DatabaseTest
import com.example.where_to_eat.helpers.RestaurantsApi
import com.example.where_to_eat.models.AppDatabase
import com.example.where_to_eat.models.FavRestaurants
import com.example.where_to_eat.models.RecyclerAdapter
import com.example.where_to_eat.models.Restaurants
import kotlinx.android.synthetic.main.fragment_profile.recycler_view
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileFragment() : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var restaurants: ArrayList<Restaurants.Restaurant>
    private lateinit var restaurantsToList: ArrayList<Restaurants.Restaurant>
    private lateinit var favList: List<FavRestaurants>
    private lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_profile, container, false)

        return view
    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            db = DatabaseTest.getDb(context)
            restaurantsToList = ArrayList()

            layoutManager = LinearLayoutManager(activity)
//            restaurants = getRestaurants()
            restaurants = RestaurantsApi.getRestaurants()

            GlobalScope.launch {
                favList = db.favRestaurantsDao().getAll()
                restaurants.forEach {


                    val index = favList.indexOfFirst { fav -> fav.restaurant_id == it.id }

                    if (index != -1) {
                        restaurantsToList.add(it)
                    }
                }

                activity?.runOnUiThread {


                    // set the custom adapter to the RecyclerView
                    adapter = RecyclerAdapter(true, this@ProfileFragment.requireContext(), restaurantsToList)
                }

            }
        }
    }

}