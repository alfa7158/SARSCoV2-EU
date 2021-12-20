package com.example.covideu.view.adapter.treatment

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.covideu.R
import com.example.covideu.model.VaccineAndTreatments.Treatment.getAllTreatmentsData

class allTreatmentRecyclerView(private val list: List<getAllTreatmentsData>) :
    RecyclerView.Adapter<allTreatmentRecyclerView.allTreatmentViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): allTreatmentRecyclerView.allTreatmentViewHolder {

        return allTreatmentViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_all_treatment_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: allTreatmentViewHolder, position: Int) {

        val item = list[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category    }

    override fun getItemCount(): Int {
        return list.size
    }


    class allTreatmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val developer: TextView = itemView.findViewById(R.id.developer_researcherAllTreatment)
        val theCategory: TextView = itemView.findViewById(R.id.categoryAllTreatment)
    }

}