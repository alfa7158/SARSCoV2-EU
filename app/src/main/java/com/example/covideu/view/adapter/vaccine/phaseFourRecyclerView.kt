package com.example.covideu.view.adapter.vaccine

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.covideu.R
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getFDA_ApprovedVaccines
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_four_vaccines
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseFourViewModel

class phaseFourRecyclerView(val viewModel:phaseFourViewModel) :
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

    val DIF_CALLBACK = object : DiffUtil.ItemCallback<getPhase_four_vaccines>(){
        override fun areItemsTheSame(oldItem: getPhase_four_vaccines, newItem: getPhase_four_vaccines): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: getPhase_four_vaccines, newItem: getPhase_four_vaccines): Boolean {
            return oldItem==newItem
        }

    }


    private val differ = AsyncListDiffer(this,DIF_CALLBACK)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: phaaseFourViewHolder, position: Int) {

        val item = differ.currentList[position]
        holder.developer.text = "Researcher:\n${ item.developerResearcher}"
        holder.theCategory.text = "category:\n${item.category}"


        holder.phaseThreeCardView.setOnClickListener {
            it.findNavController().navigate(R.id.action_phaseFourFragment_to_phaseFourFragmentDetails)
            viewModel.covid19PhaseFourLiveDataDetails.postValue(item)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list:List<getPhase_four_vaccines>){
        differ.submitList(list)

    }


    class phaaseFourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val developer: TextView = itemView.findViewById(R.id.developer_researcherPhaseFour)
        val theCategory: TextView = itemView.findViewById(R.id.categoryPhaseFour)

        val phaseThreeCardView: CardView = itemView.findViewById(R.id.phaseFourCardView)

    }

}