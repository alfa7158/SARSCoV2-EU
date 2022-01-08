package com.example.covideu.view.adapter.countriesRecyclers

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
import com.example.covideu.model.getAllAfricanCountries.getAllAfricanCountriesModel
import com.example.covideu.model.getAllAsianCountries.getAll_AsianCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.asiaViewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.s_usa_ViewModel

class showAsiaDataRecyclerView(val viewModel: asiaViewModel) :
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

        val item = differ.currentList[position]
        holder.rankOfCases.text = "Rank:${item.rank}"
        holder.countries.text = "Country: ${item.country}"
        holder.totalCases.text = "Total cases:${item.totalCases}"
        holder.cardView.setOnClickListener {

            it.findNavController().navigate(R.id.action_showAsiaDataFragment_to_asian_detailsFragment)
            viewModel.covid19AsiaLiveDataDetails.postValue(item)

        }
    }


    val DIF_CALLBACK = object : DiffUtil.ItemCallback<getAll_AsianCountriesModel>(){
        override fun areItemsTheSame(oldItem: getAll_AsianCountriesModel, newItem: getAll_AsianCountriesModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: getAll_AsianCountriesModel, newItem: getAll_AsianCountriesModel): Boolean {
            return oldItem==newItem
        }

    }
    private val differ = AsyncListDiffer(this,DIF_CALLBACK)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submitList(list:List<getAll_AsianCountriesModel>){
        differ.submitList(list)

    }

    class showCountriesDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val rankOfCases: TextView = itemView.findViewById(R.id.rank_aisa)
        val countries: TextView = itemView.findViewById(R.id.country_asia)
        val totalCases: TextView = itemView.findViewById(R.id.asiaTotalCasesCountries)
        val cardView: CardView = itemView.findViewById(R.id.cardViewAsia)

    }

}