package com.example.covideu.view.adapter.countriesRecyclers
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.covideu.R
import com.example.covideu.model.get.all.covid.Countries.Statistical.getAllCovid19CountriesStatisticalDataItemModel


class showCountriesStatisticalDataRecyclerView(private val list: List<getAllCovid19CountriesStatisticalDataItemModel>) :
  RecyclerView.Adapter<showCountriesStatisticalDataRecyclerView.showCountriesDataViewHolder>(){

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): showCountriesStatisticalDataRecyclerView.showCountriesDataViewHolder {

      return showCountriesDataViewHolder(
          LayoutInflater.from(parent.context).inflate(
              R.layout.item_all_covid_satatstical_layout,
              parent,
              false
          )
      )
  }

    override fun onBindViewHolder(holder: showCountriesDataViewHolder, position: Int) {

        val item = list[position]
        holder.rankOfCasesS.text = "Rank:${item.rank}"
        holder.countriesS.text = "Country: ${item.country}"
        holder.totalCasesS.text = "Total cases:${item.totalCases}"

    }

    override fun getItemCount(): Int {
        return list.size
    }


    class showCountriesDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val rankOfCasesS: TextView = itemView.findViewById(R.id.rank_countriesStaticalData)
        val countriesS: TextView = itemView.findViewById(R.id.countryStatisticalData)
        val totalCasesS: TextView = itemView.findViewById(R.id.countriesStatisticalTotalCases)


    }

}