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
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_two_vaccines
import com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.vaccine.phaseTwoViewModel

class phaseTwoRecyclerView(val viewModel: phaseTwoViewModel) :
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

    val DIF_CALLBACK = object : DiffUtil.ItemCallback<getPhase_two_vaccines>(){
        override fun areItemsTheSame(oldItem: getPhase_two_vaccines, newItem: getPhase_two_vaccines): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: getPhase_two_vaccines, newItem: getPhase_two_vaccines): Boolean {
            return oldItem==newItem
        }

    }


    private val differ = AsyncListDiffer(this,DIF_CALLBACK)

    override fun onBindViewHolder(holder: phaseTwoViewHolder, position: Int) {

        val item = differ.currentList[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category
        holder.phaseTwoCardView.setOnClickListener {
            it.findNavController().navigate(R.id.action_showPhaseTwoFragment_to_phasetTwoFragmentDetails)
            viewModel.covid19PhaseTwoLiveDataDetails.postValue(item)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submitList(list:List<getPhase_two_vaccines>){
        differ.submitList(list)

    }



    class phaseTwoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val developer: TextView = itemView.findViewById(R.id.developer_researcherPhaseTwo)
        val theCategory: TextView = itemView.findViewById(R.id.categoryPhaseTwo)
        val phaseTwoCardView:CardView = itemView.findViewById(R.id.phaseTwoCardView)
    }

}