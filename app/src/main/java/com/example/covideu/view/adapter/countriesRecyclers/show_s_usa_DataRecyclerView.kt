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
import com.example.covideu.model.get.all.covid.Countries.Statistical.getAllCovid19CountriesStatisticalDataItemModel
import com.example.covideu.model.getAllAfricanCountries.getAllAfricanCountriesModel
import com.example.covideu.model.getAllEuropeanCountries.getAllEuropeanCountriesModel
import com.example.covideu.model.getAllSouthernAmericanCountries.getAllSouthernAmericanCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.n_usa_viewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.s_usa_ViewModel

class show_s_usa_DataRecyclerView(val viewModel: s_usa_ViewModel) :
    RecyclerView.Adapter<show_s_usa_DataRecyclerView.showCountriesDataViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): show_s_usa_DataRecyclerView.showCountriesDataViewHolder {

        return showCountriesDataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_s_usa_layout,
                parent,
                false
            )
        )
    }

    val DIF_CALLBACK = object : DiffUtil.ItemCallback<getAllSouthernAmericanCountriesModel>(){
        override fun areItemsTheSame(oldItem: getAllSouthernAmericanCountriesModel, newItem: getAllSouthernAmericanCountriesModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: getAllSouthernAmericanCountriesModel, newItem: getAllSouthernAmericanCountriesModel): Boolean {
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


        holder.cardView .setOnClickListener {

            it.findNavController().navigate(R.id.action_showS_USA_DataFragment_to_fragment_s_usa_details2)
            viewModel.covid19SouthAmericaLiveDataDetails.postValue(item)

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submitList(list:List<getAllSouthernAmericanCountriesModel>){
        differ.submitList(list)

    }



    class showCountriesDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val rankOfCases: TextView = itemView.findViewById(R.id.rank_s_usa)
        val countries: TextView = itemView.findViewById(R.id.country_s_usa)
        val totalCases: TextView = itemView.findViewById(R.id.s_usa_TotalCasesCountries)
        val cardView: CardView = itemView.findViewById(R.id.s_usa_cardView)


    }

}