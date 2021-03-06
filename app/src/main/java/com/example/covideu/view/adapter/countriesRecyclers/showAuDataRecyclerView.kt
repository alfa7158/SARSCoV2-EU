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
import com.example.covideu.model.getAllAsianCountries.getAll_AsianCountriesModel
import com.example.covideu.model.getAllAustralianAndOceaniancounties.getAllAustralianAndOceanianCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.asiaViewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.auViewModel
/**
 * This is the Australia and oceania countries adapter which its job to show list of countries in the Australia and oceania
 */
class showAuDataRecyclerView(val viewModel: auViewModel,var fileContext:Context) :
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
        holder.countries.text = item.country
        // holder.totalCases.text = "Total cases:${item.totalCases}"
        Glide.with(fileContext)
            .load("https://countryflagsapi.com/png/${item.twoLetterSymbol}")
            .into(holder.flags)
        /**
         * the onClick listen is clicked to navigate from the recycler to the details fragment as
         * well as posts the value to covid19AustralianAndOceaniaLiveDataDetails live data list
         */
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

        //val rankOfCases: TextView = itemView.findViewById(R.id.rank_au)
        val countries: TextView = itemView.findViewById(R.id.country_au)
       // val totalCases: TextView = itemView.findViewById(R.id.auTotalCasesCountries)
        val cardView: CardView = itemView.findViewById(R.id.CardViewAu)
        val flags: ImageView = itemView.findViewById(R.id.auAndOceniaFlags)



    }

}