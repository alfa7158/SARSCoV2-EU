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
import com.example.covideu.model.VaccineAndTreatments.Treatment.getFDA_Approvedtreatments
import com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.treatment.allFdaApprovedTreatment
/**
 * This is the all fda approve treatment adapter which its job to show list of all approved treatment
 */
class getFDA_approvedTreatment_RecyclerView(private val list: List<getFDA_Approvedtreatments>,val viewModel: allFdaApprovedTreatment) :
    RecyclerView.Adapter<getFDA_approvedTreatment_RecyclerView.getFDA_approvedTreatment_viewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): getFDA_approvedTreatment_RecyclerView.getFDA_approvedTreatment_viewHolder {

        return getFDA_approvedTreatment_viewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_all_treatment__approved_layout,
                parent,
                false
            )
        )
    }

    val DIF_CALLBACK = object : DiffUtil.ItemCallback<getFDA_Approvedtreatments>(){
        override fun areItemsTheSame(oldItem: getFDA_Approvedtreatments, newItem: getFDA_Approvedtreatments): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: getFDA_Approvedtreatments, newItem: getFDA_Approvedtreatments): Boolean {
            return oldItem==newItem
        }

    }


    private val differ = AsyncListDiffer(this,DIF_CALLBACK)

    override fun onBindViewHolder(holder: getFDA_approvedTreatment_viewHolder, position: Int) {

        val item = differ.currentList[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category


        /**
         * the onClick listen is clicked to navigate from the recycler to the details fragment as
         * well as posts the value to covid19ApprovedTreatmentsLiveDataDetails live data list
         */
        holder.allFdaApprovedTreatmentCardView.setOnClickListener {

            it.findNavController().navigate(R.id.action_FDA_approved_treatmentFragment_to_allFdaApprovedTreatmentFragmentDetails)
            viewModel.covid19ApprovedTreatmentsLiveDataDetails.postValue(item)

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list:List<getFDA_Approvedtreatments>){
        differ.submitList(list)

    }


    class getFDA_approvedTreatment_viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val developer: TextView = itemView.findViewById(R.id.developer_researcherAlApprovedTreatment)
        val theCategory: TextView = itemView.findViewById(R.id.categoryAllApprovedTreatment)
        val allFdaApprovedTreatmentCardView: CardView = itemView.findViewById(R.id.allFdaApprovedTreatmentCardView)


    }

}