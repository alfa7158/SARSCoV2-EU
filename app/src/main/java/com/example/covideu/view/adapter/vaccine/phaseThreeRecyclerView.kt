package com.example.covideu.view.adapter.vaccine

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.example.covideu.R
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_three_vaccines
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseThreeViewModel

class phaseThreeRecyclerView(private val list: List<getPhase_three_vaccines>,val viewModel: phaseThreeViewModel) :
    RecyclerView.Adapter<phaseThreeRecyclerView.phaseThreeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): phaseThreeRecyclerView.phaseThreeViewHolder {

        return phaseThreeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_phase_three_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: phaseThreeViewHolder, position: Int) {

        val item = list[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category

        holder.phaseThreeCardView.setOnClickListener {
            it.findNavController().navigate(R.id.action_showPhaseThreeFragment_to_phaseThreeFragmentDetails)
            viewModel.covid19PhaseThreeLiveDataDetails.postValue(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class phaseThreeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val developer: TextView = itemView.findViewById(R.id.developer_researcherPhaseThree)
        val theCategory: TextView = itemView.findViewById(R.id.categoryPhaseThree)

        val phaseThreeCardView: CardView = itemView.findViewById(R.id.phaseThreeCardView)

    }

}