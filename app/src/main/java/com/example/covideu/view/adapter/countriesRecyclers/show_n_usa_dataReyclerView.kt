package com.example.covideu.view.adapter.countriesRecyclers

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.covideu.R
import com.example.covideu.model.getAllNorthernAmericanCountries.getAllNorthernAmericanCountriesModel
import com.example.covideu.model.getAllSouthernAmericanCountries.getAllSouthernAmericanCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.africaViewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.n_usa_viewModel

/**
 * This is the North USA countries adapter which its job to show list of countries in the N-USA
 */
class show_n_usa_dataReyclerView(val viewModel: n_usa_viewModel) :
    RecyclerView.Adapter<show_n_usa_dataReyclerView.showCountriesDataViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): show_n_usa_dataReyclerView.showCountriesDataViewHolder {

        return showCountriesDataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_n_usa_layout,
                parent,
                false
            )
        )
    }

    val DIF_CALLBACK = object : DiffUtil.ItemCallback<getAllNorthernAmericanCountriesModel>(){
        override fun areItemsTheSame(oldItem: getAllNorthernAmericanCountriesModel, newItem: getAllNorthernAmericanCountriesModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: getAllNorthernAmericanCountriesModel, newItem: getAllNorthernAmericanCountriesModel): Boolean {
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

        /**
         * the onClick listen is clicked to navigate from the recycler to the details fragment as
         * well as posts the value to covid19NorthAmericaLiveDataDetails live data list
         */
        holder.cardView.setOnClickListener {

            it.findNavController().navigate(R.id.action_showN_USA_DataFragment_to_fragment_n_usa_details)
            viewModel.covid19NorthAmericaLiveDataDetails.postValue(item)

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submitList(list:List<getAllNorthernAmericanCountriesModel>){
        differ.submitList(list)

    }

    class showCountriesDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val rankOfCases: TextView = itemView.findViewById(R.id.rank_n_usa)
        val countries: TextView = itemView.findViewById(R.id.country_n_usa)
        val totalCases: TextView = itemView.findViewById(R.id.n_usa_TotalCasesCountries)
        val cardView: CardView = itemView.findViewById(R.id.n_usa_cardView)



    }

}