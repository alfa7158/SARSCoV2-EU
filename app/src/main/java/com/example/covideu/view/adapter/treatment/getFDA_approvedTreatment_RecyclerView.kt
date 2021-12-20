package com.example.covideu.view.adapter.treatment

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.covideu.R
import com.example.covideu.model.VaccineAndTreatments.Treatment.getFDA_Approvedtreatments

class getFDA_approvedTreatment_RecyclerView(private val list: List<getFDA_Approvedtreatments>) :
    RecyclerView.Adapter<getFDA_approvedTreatment_RecyclerView.getFDA_approvedTreatment_viewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): getFDA_approvedTreatment_RecyclerView.getFDA_approvedTreatment_viewHolder {

        return getFDA_approvedTreatment_viewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_all_treatment__approved_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: getFDA_approvedTreatment_viewHolder, position: Int) {

        val item = list[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class getFDA_approvedTreatment_viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val developer: TextView = itemView.findViewById(R.id.developer_researcherAlApprovedTreatment)
        val theCategory: TextView = itemView.findViewById(R.id.categoryAllApprovedTreatment)
    }

}