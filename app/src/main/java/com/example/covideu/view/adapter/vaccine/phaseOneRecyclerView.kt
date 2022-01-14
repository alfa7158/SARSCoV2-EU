package com.example.covideu.view.adapter.vaccine

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
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_one_vaccines
import com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.vaccine.phaseOneViewModel

class phaseOneRecyclerView(val viewModel: phaseOneViewModel) :
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
    val DIF_CALLBACK = object : DiffUtil.ItemCallback<getPhase_one_vaccines>(){
        override fun areItemsTheSame(oldItem: getPhase_one_vaccines, newItem: getPhase_one_vaccines): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: getPhase_one_vaccines, newItem: getPhase_one_vaccines): Boolean {
            return oldItem==newItem
        }

    }


    private val differ = AsyncListDiffer(this,DIF_CALLBACK)
    override fun onBindViewHolder(holder: phaseOneViewHolder, position: Int) {

        val item = differ.currentList[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category

        holder.phaseOneCardView.setOnClickListener{
            it.findNavController().navigate(R.id.action_showPhaseOneFragment_to_phaseOneFragmentDetails)
            viewModel.covid19PhaseOneLiveDataDetails.postValue(item)


        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list:List<getPhase_one_vaccines>){
        differ.submitList(list)

    }

    class phaseOneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val developer: TextView = itemView.findViewById(R.id.developer_researcherPhaseOne)
        val theCategory: TextView = itemView.findViewById(R.id.categoryPhaseOne)
        val phaseOneCardView:CardView = itemView.findViewById(R.id.phaseOneCarview)

    }

}