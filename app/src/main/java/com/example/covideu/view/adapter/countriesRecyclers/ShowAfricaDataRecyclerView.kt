package com.example.covideu.view.adapter.countriesRecyclers

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.example.covideu.R
import com.example.covideu.model.getAllAfricanCountries.getAllAfricanCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.africaViewModel

class showAfricaDataRecyclerView(private val list: MutableList<getAllAfricanCountriesModel>,val viewModel:africaViewModel) :
    RecyclerView.Adapter<showAfricaDataRecyclerView.showCountriesDataViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): showCountriesDataViewHolder {

        return showCountriesDataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_africa_data_layout,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: showCountriesDataViewHolder, position: Int) {

        val item = list[position]
        holder.rankOfCases.text = "Rank:${item.rank}"
        holder.countries.text = "Country: ${item.country}"
        holder.totalCases.text = "Total cases:${item.totalCases}"

        holder.cardView.setOnClickListener {

            it.findNavController().navigate(R.id.action_showCountriesDataFagment_to_countries_detailsFragment)
            viewModel.covid19AfricaLiveData.postValue(listOf(item))

        }


    }

    override fun getItemCount(): Int {
        return list.size
    }


    class showCountriesDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val rankOfCases:TextView = itemView.findViewById(R.id.rank_covidCountries)
        val countries:TextView = itemView.findViewById(R.id.country_coivdCountries)
        val totalCases:TextView = itemView.findViewById(R.id.totalCasesCountries)
        val cardView:CardView = itemView.findViewById(R.id.africanCardView)


    }

}