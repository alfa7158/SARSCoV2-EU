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
import com.example.covideu.model.getAllAustralianAndOceaniancounties.getAllAustralianAndOceanianCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.asiaViewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.auViewModel

class showAuDataRecyclerView(private val list: List<getAllAustralianAndOceanianCountriesModel>,val viewModel: auViewModel) :
    RecyclerView.Adapter<showAuDataRecyclerView.showCountriesDataViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): showAuDataRecyclerView.showCountriesDataViewHolder {

        return showCountriesDataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_au_layout,
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

            it.findNavController().navigate(R.id.action_showAU_DataFragmentFragment_to_fragment_au_details)
            viewModel.covid19AustralianAndOceaniaLiveDataDetails.postValue(item)

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class showCountriesDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val rankOfCases: TextView = itemView.findViewById(R.id.rank_au)
        val countries: TextView = itemView.findViewById(R.id.country_au)
        val totalCases: TextView = itemView.findViewById(R.id.auTotalCasesCountries)
        val cardView: CardView = itemView.findViewById(R.id.CardViewAu)


    }

}