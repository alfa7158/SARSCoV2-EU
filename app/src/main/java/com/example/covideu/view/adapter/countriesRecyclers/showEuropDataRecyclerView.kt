package com.example.covideu.view.adapter.countriesRecyclers

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.covideu.R
import com.example.covideu.model.getAllEuropeanCountries.getAllEuropeanCountriesModel

class showEuropDataRecyclerView(private val list: List<getAllEuropeanCountriesModel>) :
    RecyclerView.Adapter<showEuropDataRecyclerView.showCountriesDataViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): showEuropDataRecyclerView.showCountriesDataViewHolder {

        return showCountriesDataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_europ_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: showCountriesDataViewHolder, position: Int) {

        val item = list[position]
        holder.rankOfCases.text = "Rank:${item.rank}"
        holder.countries.text = "Country: ${item.country}"
        holder.totalCases.text = "Total cases:${item.totalCases}"

    }

    override fun getItemCount(): Int {
        return list.size
    }


    class showCountriesDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val rankOfCases: TextView = itemView.findViewById(R.id.rank_europ)
        val countries: TextView = itemView.findViewById(R.id.country_europ)
        val totalCases: TextView = itemView.findViewById(R.id.europTotalCasesCountries)


    }
}