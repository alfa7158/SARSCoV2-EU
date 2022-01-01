package com.example.covideu.view.adapter.vaccine

import android.service.carrier.CarrierService
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.example.covideu.R
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_two_vaccines
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseOneViewModel
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseTwoViewModel

class phaseTwoRecyclerView(private val list: List<getPhase_two_vaccines>,val viewModel: phaseTwoViewModel) :
    RecyclerView.Adapter<phaseTwoRecyclerView.phaseTwoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): phaseTwoRecyclerView.phaseTwoViewHolder {

        return phaseTwoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_phase_two_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: phaseTwoViewHolder, position: Int) {

        val item = list[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category
        holder.phaseTwoCardView.setOnClickListener {
            it.findNavController().navigate(R.id.action_showPhaseTwoFragment_to_phasetTwoFragmentDetails)
            viewModel.covid19PhaseTwoLiveDataDetails.postValue(item)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


    class phaseTwoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val developer: TextView = itemView.findViewById(R.id.developer_researcherPhaseTwo)
        val theCategory: TextView = itemView.findViewById(R.id.categoryPhaseTwo)
        val phaseTwoCardView:CardView = itemView.findViewById(R.id.phaseTwoCardView)
    }

}