package com.example.finalexam.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalexam.R
import com.example.finalexam.data.api.ApiService
import com.example.finalexam.data.models.CountryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryListFragment : Fragment(), CountryListFragmentAdapter.OnItemClickListener {

    lateinit var countryList: List<CountryData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_country_list, container, false)
        val apiService = ApiService()

        apiService.getCountryData().enqueue(object: Callback<List<CountryData>> {
            override fun onResponse(
                call: Call<List<CountryData>>,
                response: Response<List<CountryData>>
            ) {
                countryList = response.body()!!
                createRecyclerView(view)
            }

            override fun onFailure(call: Call<List<CountryData>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })



        return view
    }

    fun createRecyclerView(view: View) {
        val recyclerView: RecyclerView =  view.findViewById(R.id.recyclerView)
        val adapter = CountryListFragmentAdapter(countryList, context, this)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        val clickedItem = countryList[position]
        val clickedItemSlug = clickedItem.slug
        view?.let {
            val actionDetail = CountryListFragmentDirections.actionCountryListFragmentToCountryStatsFragment(clickedItemSlug)
            Navigation.findNavController(it).navigate(actionDetail)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CountryListFragment()
    }


}