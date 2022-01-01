package com.example.covideu.view.adapter.vaccine

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.example.covideu.R
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_four_vaccines
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseFourViewModel

class phaseFourRecyclerView(private val list: List<getPhase_four_vaccines>,val viewModel:phaseFourViewModel) :
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


        holder.phaseThreeCardView.setOnClickListener {
            it.findNavController().navigate(R.id.action_phaseFourFragment_to_phaseFourFragmentDetails)
            viewModel.covid19PhaseFourLiveDataDetails.postValue(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class phaaseFourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val developer: TextView = itemView.findViewById(R.id.developer_researcherPhaseFour)
        val theCategory: TextView = itemView.findViewById(R.id.categoryPhaseFour)

        val phaseThreeCardView: CardView = itemView.findViewById(R.id.phaseFourCardView)

    }

}