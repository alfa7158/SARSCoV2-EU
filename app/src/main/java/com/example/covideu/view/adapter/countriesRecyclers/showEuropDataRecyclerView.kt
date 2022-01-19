package com.example.covideu.view.adapter.countriesRecyclers

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.covideu.R
import com.example.covideu.model.getAllAustralianAndOceaniancounties.getAllAustralianAndOceanianCountriesModel
import com.example.covideu.model.getAllEuropeanCountries.getAllEuropeanCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.auViewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.europViewModel


/**
 * This is the Europe countries adapter which its job to show list of countries in the Australia and oceania
 */
class showEuropDataRecyclerView(val viewModel: europViewModel,var fileContext:Context) :
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
    val DIF_CALLBACK = object : DiffUtil.ItemCallback<getAllEuropeanCountriesModel>(){
        override fun areItemsTheSame(oldItem: getAllEuropeanCountriesModel, newItem: getAllEuropeanCountriesModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: getAllEuropeanCountriesModel, newItem: getAllEuropeanCountriesModel): Boolean {
            return oldItem==newItem
        }

    }


    private val differ = AsyncListDiffer(this,DIF_CALLBACK)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: showCountriesDataViewHolder, position: Int) {

        val item = differ.currentList[position]
        holder.countries.text = item.country
        // holder.totalCases.text = "Total cases:${item.totalCases}"
        Glide.with(fileContext)
            .load("https://countryflagsapi.com/png/${item.twoLetterSymbol}")
            .into(holder.flags)

        /**
         * the onClick listen is clicked to navigate from the recycler to the details fragment as
         * well as posts the value to covid19EuropeLiveDataDetails live data list
         */
        holder.cardView.setOnClickListener {

            it.findNavController().navigate(R.id.action_showEuropDataFragment_to_fragment_europe_details)
            viewModel.covid19EuropeLiveDataDetails.postValue(item)

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submitList(list:List<getAllEuropeanCountriesModel>){
        differ.submitList(list)

    }

    class showCountriesDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

       // val rankOfCases: TextView = itemView.findViewById(R.id.rank_europ)
        val countries: TextView = itemView.findViewById(R.id.country_europ)
       //val totalCases: TextView = itemView.findViewById(R.id.europTotalCasesCountries)
        val cardView: CardView = itemView.findViewById(R.id.CardViewEurope)
        val flags: ImageView = itemView.findViewById(R.id.europFlags)


    }
}