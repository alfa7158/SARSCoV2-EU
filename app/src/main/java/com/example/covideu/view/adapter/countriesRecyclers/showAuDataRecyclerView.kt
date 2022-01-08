package com.example.covideu.view.adapter.countriesRecyclers

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.covideu.R
import com.example.covideu.model.getAllAsianCountries.getAll_AsianCountriesModel
import com.example.covideu.model.getAllAustralianAndOceaniancounties.getAllAustralianAndOceanianCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.asiaViewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.auViewModel

class showAuDataRecyclerView(val viewModel: auViewModel) :
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

    val DIF_CALLBACK = object : DiffUtil.ItemCallback<getAllAustralianAndOceanianCountriesModel>(){
        override fun areItemsTheSame(oldItem: getAllAustralianAndOceanianCountriesModel, newItem: getAllAustralianAndOceanianCountriesModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: getAllAustralianAndOceanianCountriesModel, newItem: getAllAustralianAndOceanianCountriesModel): Boolean {
            return oldItem==newItem
        }

    }
    private val differ = AsyncListDiffer(this,DIF_CALLBACK)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: showCountriesDataViewHolder, position: Int) {

        val item = differ.currentList[position]
        holder.rankOfCases.text = "Rank:${item.rank}"
        holder.countries.text = "Country: ${item.country}"
        holder.totalCases.text = "Total cases:${item.totalCases}"
        holder.cardView.setOnClickListener {

            it.findNavController().navigate(R.id.action_showAU_DataFragmentFragment_to_fragment_au_details)
            viewModel.covid19AustralianAndOceaniaLiveDataDetails.postValue(item)

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submitList(list:List<getAllAustralianAndOceanianCountriesModel>){
        differ.submitList(list)

    }


    class showCountriesDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val rankOfCases: TextView = itemView.findViewById(R.id.rank_au)
        val countries: TextView = itemView.findViewById(R.id.country_au)
        val totalCases: TextView = itemView.findViewById(R.id.auTotalCasesCountries)
        val cardView: CardView = itemView.findViewById(R.id.CardViewAu)


    }

}