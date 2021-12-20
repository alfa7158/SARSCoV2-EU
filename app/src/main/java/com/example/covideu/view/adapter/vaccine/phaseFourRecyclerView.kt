package com.example.covideu.view.adapter.vaccine

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.covideu.R
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_four_vaccines

class phaseFourRecyclerView(private val list: List<getPhase_four_vaccines>) :
    RecyclerView.Adapter<phaseFourRecyclerView.phaaseFourViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): phaseFourRecyclerView.phaaseFourViewHolder {

        return phaaseFourViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_phase_four_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: phaaseFourViewHolder, position: Int) {

        val item = list[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class phaaseFourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val developer: TextView = itemView.findViewById(R.id.developer_researcherPhaseFour)
        val theCategory: TextView = itemView.findViewById(R.id.categoryPhaseFour)
    }

}