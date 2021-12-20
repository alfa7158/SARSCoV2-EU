package com.example.covideu.view.adapter.vaccine

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.covideu.R
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_one_vaccines

class phaseOneRecyclerView(private val list: List<getPhase_one_vaccines>) :
    RecyclerView.Adapter<phaseOneRecyclerView.phaseOneViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): phaseOneRecyclerView.phaseOneViewHolder {

        return phaseOneViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_phase_one_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: phaseOneViewHolder, position: Int) {

        val item = list[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class phaseOneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val developer: TextView = itemView.findViewById(R.id.developer_researcherPhaseOne)
        val theCategory: TextView = itemView.findViewById(R.id.categoryPhaseOne)
    }

}