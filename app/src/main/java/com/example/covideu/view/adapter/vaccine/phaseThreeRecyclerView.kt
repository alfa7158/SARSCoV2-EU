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
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_three_vaccines
import com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.vaccine.phaseThreeViewModel
/**
 * This is the all vaccine adapter which its job to show list of all
 * vaccine that are in phase 3
 */
class phaseThreeRecyclerView(val viewModel: phaseThreeViewModel) :
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
    val DIF_CALLBACK = object : DiffUtil.ItemCallback<getPhase_three_vaccines>(){
        override fun areItemsTheSame(oldItem: getPhase_three_vaccines, newItem: getPhase_three_vaccines): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: getPhase_three_vaccines, newItem: getPhase_three_vaccines): Boolean {
            return oldItem==newItem
        }

    }


    private val differ = AsyncListDiffer(this,DIF_CALLBACK)

    override fun onBindViewHolder(holder: phaseThreeViewHolder, position: Int) {

        val item = differ.currentList[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category


        /**
         * the onClick listen is clicked to navigate from the recycler to the details fragment as
         * well as posts the value to covid19PhaseThreeLiveDataDetails live data list
         */
        holder.phaseThreeCardView.setOnClickListener {
            it.findNavController().navigate(R.id.action_showPhaseThreeFragment_to_phaseThreeFragmentDetails)
            viewModel.covid19PhaseThreeLiveDataDetails.postValue(item)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submitList(list:List<getPhase_three_vaccines>){
        differ.submitList(list)

    }


    class phaseThreeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val developer: TextView = itemView.findViewById(R.id.developer_researcherPhaseThree)
        val theCategory: TextView = itemView.findViewById(R.id.categoryPhaseThree)

        val phaseThreeCardView: CardView = itemView.findViewById(R.id.phaseThreeCardView)

    }

}