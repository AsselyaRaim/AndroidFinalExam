package com.example.finalexam.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalexam.R
import com.example.finalexam.data.models.CountryData

class CountryListFragmentAdapter(val countryList: List<CountryData>, val context: Context?, val listener: OnItemClickListener): RecyclerView.Adapter<CountryListFragmentAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val country = countryList[position]
        holder.country.text = country.country
        holder.iso2Code.text = holder.iso2Code.text.toString() + country.iSO2
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener{

        var country = view.findViewById<TextView>(R.id.countryName)
        val iso2Code = view.findViewById<TextView>(R.id.iso2Code)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}