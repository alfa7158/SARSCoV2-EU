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
import com.example.covideu.model.VaccineAndTreatments.Treatment.getClinicalTreatments
import com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.treatment.allClinicalViewModel

class showAllClinicalTreatmentRecyclerView(val viewModel: allClinicalViewModel) :
    RecyclerView.Adapter<showAllClinicalTreatmentRecyclerView.showAllClinicalTreatmentViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): showAllClinicalTreatmentRecyclerView.showAllClinicalTreatmentViewHolder {

        return showAllClinicalTreatmentViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_all_clinical_layout,
                parent,
                false
            )
        )
    }

    val DIF_CALLBACK = object : DiffUtil.ItemCallback<getClinicalTreatments>(){
        override fun areItemsTheSame(oldItem: getClinicalTreatments, newItem: getClinicalTreatments): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: getClinicalTreatments, newItem: getClinicalTreatments): Boolean {
            return oldItem==newItem
        }

    }


    private val differ = AsyncListDiffer(this,DIF_CALLBACK)

    override fun onBindViewHolder(holder: showAllClinicalTreatmentViewHolder, position: Int) {

        val item = differ.currentList[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category

        holder.allClinicalTreatmentCardView.setOnClickListener {

            it.findNavController().navigate(R.id.action_clinicalTreatmentFragment_to_allClinicalTreatmentFragmentDetails)
            viewModel.covid19ClinicalLiveDataDetails.postValue(item)
        }

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list:List<getClinicalTreatments>){
        differ.submitList(list)

    }


    class showAllClinicalTreatmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val developer: TextView = itemView.findViewById(R.id.developer_researcherClinicalTreatment)
        val theCategory: TextView = itemView.findViewById(R.id.categoryClinicalTreatment)
        val allClinicalTreatmentCardView: CardView = itemView.findViewById(R.id.allClinicalCardView)

    }

}