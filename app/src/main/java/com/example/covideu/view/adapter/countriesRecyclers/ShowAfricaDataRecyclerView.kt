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
import com.example.covideu.model.getAllAfricanCountries.getAllAfricanCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.africaViewModel
/**
 * This is the African countries adapter which its job to show list of countries in the African
 */
class showAfricaDataRecyclerView(val viewModel:africaViewModel,var fileContext:Context) :
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

    val DIF_CALLBACK = object : DiffUtil.ItemCallback<getAllAfricanCountriesModel>(){
        override fun areItemsTheSame(oldItem: getAllAfricanCountriesModel, newItem: getAllAfricanCountriesModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: getAllAfricanCountriesModel, newItem: getAllAfricanCountriesModel): Boolean {
            return oldItem==newItem
        }

    }
    private val differ = AsyncListDiffer(this,DIF_CALLBACK)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: showCountriesDataViewHolder, position: Int) {

        val item = differ.currentList[position]
        holder.countries.text = item.country
        Glide.with(fileContext)
            .load("https://countryflagsapi.com/png/${item.twoLetterSymbol}")
            .into(holder.flags)
        /**
         * the onClick listen is clicked to navigate from the recycler to the details fragment as
         * well as posts the value to ovid19AfricaLiveDataDetails live data list
         */
        holder.cardView.setOnClickListener {

            it.findNavController().navigate(R.id.action_showCountriesDataFagment_to_countries_detailsFragment)
            viewModel.covid19AfricaLiveDataDetails.postValue(item)

        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list:List<getAllAfricanCountriesModel>){
        differ.submitList(list)

    }


    class showCountriesDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

      //  val rankOfCases:TextView = itemView.findViewById(R.id.rank_covidCountries)
        val countries:TextView = itemView.findViewById(R.id.country_coivdCountries)
        //val totalCases:TextView = itemView.findViewById(R.id.totalCasesCountries)
        val cardView:CardView = itemView.findViewById(R.id.africanCardView)
        val flags: ImageView = itemView.findViewById(R.id.africaFlagsImageView)


    }

}