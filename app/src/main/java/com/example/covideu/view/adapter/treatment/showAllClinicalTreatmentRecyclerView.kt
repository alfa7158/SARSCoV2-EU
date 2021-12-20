package com.example.covideu.view.adapter.treatment

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.covideu.R
import com.example.covideu.model.VaccineAndTreatments.Treatment.getClinicalTreatments

class showAllClinicalTreatmentRecyclerView(private val list: List<getClinicalTreatments>) :
    RecyclerView.Adapter<showAllClinicalTreatmentRecyclerView.showAllClinicalTreatmentViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): showAllClinicalTreatmentRecyclerView.showAllClinicalTreatmentViewHolder {

        return showAllClinicalTreatmentViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_all_clinical_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: showAllClinicalTreatmentViewHolder, position: Int) {

        val item = list[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category

    }

    override fun getItemCount(): Int {
        return list.size
    }


    class showAllClinicalTreatmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val developer: TextView = itemView.findViewById(R.id.developer_researcherClinicalTreatment)
        val theCategory: TextView = itemView.findViewById(R.id.categoryClinicalTreatment)
    }

}