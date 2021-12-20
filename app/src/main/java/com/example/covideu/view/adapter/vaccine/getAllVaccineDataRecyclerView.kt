package com.example.covideu.view.adapter.vaccine

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.covideu.R
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getAllVaccinesDataItem

class getAllVaccineDataRecyclerView(private val list: List<getAllVaccinesDataItem>) :
    RecyclerView.Adapter<getAllVaccineDataRecyclerView.getAllVaccineDataViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): getAllVaccineDataRecyclerView.getAllVaccineDataViewHolder {

        return getAllVaccineDataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_all_vaccine_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: getAllVaccineDataViewHolder, position: Int) {

        val item = list[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class getAllVaccineDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val developer: TextView = itemView.findViewById(R.id.developer_researcherAllVaccineNews)
        val theCategory: TextView = itemView.findViewById(R.id.categoryAllVaccineTitle)
    }

}