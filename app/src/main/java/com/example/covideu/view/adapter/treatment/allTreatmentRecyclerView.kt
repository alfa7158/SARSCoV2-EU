package com.example.covideu.view.adapter.treatment

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
import com.example.covideu.model.VaccineAndTreatments.Treatment.getAllTreatmentsData
import com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.treatment.allTreatmentViewModel
/**
 * This is the all treatment adapter which its job to show list of all treatment
 */
class allTreatmentRecyclerView(val viewModel: allTreatmentViewModel) :
    RecyclerView.Adapter<allTreatmentRecyclerView.allTreatmentViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): allTreatmentRecyclerView.allTreatmentViewHolder {

        return allTreatmentViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_all_treatment_layout,
                parent,
                false
            )
        )
    }
    val DIF_CALLBACK = object : DiffUtil.ItemCallback<getAllTreatmentsData>(){
        override fun areItemsTheSame(oldItem: getAllTreatmentsData, newItem: getAllTreatmentsData): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: getAllTreatmentsData, newItem: getAllTreatmentsData): Boolean {
            return oldItem==newItem
        }

    }


    private val differ = AsyncListDiffer(this,DIF_CALLBACK)

    override fun onBindViewHolder(holder: allTreatmentViewHolder, position: Int) {

        val item = differ.currentList[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category


        /**
         * the onClick listen is clicked to navigate from the recycler to the details fragment as
         * well as posts the value to covidAllTreatmentsLiveDataDetails live data list
         */
        holder.allTreatmentCardView.setOnClickListener {

            it.findNavController().navigate(R.id.action_allTreatmentFragment_to_allTreatmentFragmentDetails)
            viewModel.covidAllTreatmentsLiveDataDetails.postValue(item)

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submitList(list:List<getAllTreatmentsData>){
        differ.submitList(list)

    }


    class allTreatmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val developer: TextView = itemView.findViewById(R.id.developer_researcherAllTreatment)
        val theCategory: TextView = itemView.findViewById(R.id.categoryAllTreatment)
        val allTreatmentCardView:CardView = itemView.findViewById(R.id.allTreatmentInfoCardView)
    }

}