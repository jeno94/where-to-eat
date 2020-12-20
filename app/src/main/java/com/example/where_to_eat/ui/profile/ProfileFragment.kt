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
//        profileViewModel =
//            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_profile, container, false)
////        val textView: TextView = root.findViewById(R.id.text_notifications)
////        profileViewModel.text.observe(viewLifecycleOwner, Observer {
////            textView.text = it
////        })
//
//        return root


        val view: View = inflater!!.inflate(R.layout.fragment_profile, container, false)
//        btn = view.findViewById(R.id.favBtn)
//
//        btn?.setOnClickListener{
//            Log.d("Response", "country list size : $it")
//
//        }

//    val clickListener = View.OnClickListener{ view->
//        var x = null
//        Log.d("Test", "view: ${view.id}")
//    }
//        btn?.setOnClickListener(clickListener)





//        return inflater.inflate(R.layout.fragment_listing, container, false)

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














//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
////        restaurants = getRestaurants()
////        restaurantsToList = restaurants
//
//        return inflater.inflate(R.layout.fragment_profile, container, false)
//    }
//
//    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(itemView, savedInstanceState)
//
//        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//        StrictMode.setThreadPolicy(policy)
//        recycler_view.apply {
//            // set a LinearLayoutManager to handle Android
//            // RecyclerView behavior
//            layoutManager = LinearLayoutManager(activity)
//            // set the custom adapter to the RecyclerView
////            restaurants = getRestaurants()
////            restaurantsToList = restaurants
//
////            ListingFragment
//            adapter = RecyclerAdapter(this@ProfileFragment.requireContext() , "") //not good
////            adapter = RecyclerAdapter(this@ListingFragment.requireContext() , getRestaurants())
//
//        }
//    }
}