package com.example.covideu.view.adapter.countriesRecyclers

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.example.covideu.R
import com.example.covideu.model.getAllAsianCountries.getAll_AsianCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.asiaViewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.s_usa_ViewModel

class showAsiaDataRecyclerView(private val list: List<getAll_AsianCountriesModel>,val viewModel: asiaViewModel) :
    RecyclerView.Adapter<showAsiaDataRecyclerView.showCountriesDataViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): showAsiaDataRecyclerView.showCountriesDataViewHolder {

        return showCountriesDataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_asia_layout,
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
        holder.cardView.setOnClickListener {

            it.findNavController().navigate(R.id.action_showAsiaDataFragment_to_asian_detailsFragment)
            viewModel.covid19AsiaLiveDataDetails.postValue(item)

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class showCountriesDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val rankOfCases: TextView = itemView.findViewById(R.id.rank_aisa)
        val countries: TextView = itemView.findViewById(R.id.country_asia)
        val totalCases: TextView = itemView.findViewById(R.id.asiaTotalCasesCountries)
        val cardView: CardView = itemView.findViewById(R.id.cardViewAsia)

    }

}