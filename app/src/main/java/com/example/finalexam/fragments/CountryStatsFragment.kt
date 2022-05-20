package com.example.finalexam.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.finalexam.R
import com.example.finalexam.data.api.ApiService
import com.example.finalexam.data.models.CountryStats
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryStatsFragment : Fragment() {

    val args: CountryStatsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_country_stats, container, false)
        val apiService = ApiService()
        val slug = args.slug

        val id = view.findViewById<TextView>(R.id.id_text)
        val country = view.findViewById<TextView>(R.id.country_text)
        val province = view.findViewById<TextView>(R.id.province_text)
        val city = view.findViewById<TextView>(R.id.city_text)
        val confirmed = view.findViewById<TextView>(R.id.confirmed_text)
        val deaths = view.findViewById<TextView>(R.id.deaths_text)
        val recovered = view.findViewById<TextView>(R.id.recovered_text)
        val active = view.findViewById<TextView>(R.id.active_text)
        val date = view.findViewById<TextView>(R.id.date_text)

        apiService.getCountryStats(slug).enqueue(object : Callback<List<CountryStats>> {
            override fun onResponse(
                call: Call<List<CountryStats>>,
                response: Response<List<CountryStats>>
            ) {
                val countryStatsList = response.body()!!

                if (countryStatsList.isNotEmpty()) {
                    val latestCountryStats = countryStatsList.last()

                    id.text = id.text.toString() + latestCountryStats.id
                    country.text = country.text.toString() + latestCountryStats.country
                    province.text = province.text.toString() + latestCountryStats.province
                    city.text = city.text.toString() + latestCountryStats.city
                    confirmed.text = confirmed.text.toString() + latestCountryStats.confirmed.toString()
                    deaths.text = deaths.text.toString() + latestCountryStats.deaths.toString()
                    recovered.text = recovered.text.toString() + latestCountryStats.recovered.toString()
                    active.text = active.text.toString() + latestCountryStats.active.toString()
                    date.text = date.text.toString() + latestCountryStats.date.slice(0..10)
                }
            }

            override fun onFailure(call: Call<List<CountryStats>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })


        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CountryStatsFragment()
    }
}