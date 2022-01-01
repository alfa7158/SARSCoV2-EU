package com.example.covideu.view.adapter.vaccine

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.example.covideu.R
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_one_vaccines
import com.example.covideu.view.ViewModels.countriesDataViewModels.n_usa_viewModel
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseOneViewModel

class phaseOneRecyclerView(private val list: List<getPhase_one_vaccines>,val viewModel: phaseOneViewModel) :
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

        holder.phaseOneCardView.setOnClickListener{
            it.findNavController().navigate(R.id.action_showPhaseOneFragment_to_phaseOneFragmentDetails)
            viewModel.covid19PhaseOneLiveDataDetails.postValue(item)


        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class phaseOneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val developer: TextView = itemView.findViewById(R.id.developer_researcherPhaseOne)
        val theCategory: TextView = itemView.findViewById(R.id.categoryPhaseOne)
        val phaseOneCardView:CardView = itemView.findViewById(R.id.phaseOneCarview)

    }

}