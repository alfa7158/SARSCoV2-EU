package com.example.covideu.view.adapter.vaccine

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.covideu.R
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_two_vaccines

class phaseTwoRecyclerView(private val list: List<getPhase_two_vaccines>) :
    RecyclerView.Adapter<phaseTwoRecyclerView.phaseTwoViewModel>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): phaseTwoRecyclerView.phaseTwoViewModel {

        return phaseTwoViewModel(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_phase_two_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: phaseTwoViewModel, position: Int) {

        val item = list[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category

    }

    override fun getItemCount(): Int {
        return list.size
    }


    class phaseTwoViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val developer: TextView = itemView.findViewById(R.id.developer_researcherPhaseTwo)
        val theCategory: TextView = itemView.findViewById(R.id.categoryPhaseTwo)
    }

}