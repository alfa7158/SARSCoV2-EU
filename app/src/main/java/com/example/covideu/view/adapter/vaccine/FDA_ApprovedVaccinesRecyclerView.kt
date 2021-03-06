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
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getFDA_ApprovedVaccines
import com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.vaccine.fdaApprovedVaccineViewModel
/**
 * This is the all FDA approved vaccine adapter which its job to show list of all FDA approved
 * vaccine
 */
class FDA_ApprovedVaccinesRecyclerView(val viewModel: fdaApprovedVaccineViewModel) :
    RecyclerView.Adapter<FDA_ApprovedVaccinesRecyclerView.FDA_ApprovedVaccinesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FDA_ApprovedVaccinesRecyclerView.FDA_ApprovedVaccinesViewHolder {

        return FDA_ApprovedVaccinesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_all_fda_approve_vaccine_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FDA_ApprovedVaccinesViewHolder, position: Int) {

        val item = differ.currentList[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category


        /**
         * the onClick listen is clicked to navigate from the recycler to the details fragment as
         * well as posts the value to covid19FDAApprovedVaccineLiveDataDetails live data list
         */
        holder.fdaApprovedVaccineCardView.setOnClickListener {
            it.findNavController().navigate(R.id.action_showFDA_approved_vaccineFragment_to_fdaApprovedVaccineFragmentDetails)
            viewModel.covid19FDAApprovedVaccineLiveDataDetails.postValue(item)
        }
    }

    val DIF_CALLBACK = object : DiffUtil.ItemCallback<getFDA_ApprovedVaccines>(){
        override fun areItemsTheSame(oldItem: getFDA_ApprovedVaccines, newItem: getFDA_ApprovedVaccines): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: getFDA_ApprovedVaccines, newItem: getFDA_ApprovedVaccines): Boolean {
            return oldItem==newItem
        }

    }


    private val differ = AsyncListDiffer(this,DIF_CALLBACK)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list:List<getFDA_ApprovedVaccines>){
        differ.submitList(list)

    }

    class FDA_ApprovedVaccinesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val developer: TextView = itemView.findViewById(R.id.developer_researcherAllFdaVaccine)
        val theCategory: TextView = itemView.findViewById(R.id.categoryAllFdaVaccine)
        val fdaApprovedVaccineCardView: CardView = itemView.findViewById(R.id.allApprovedVaccineCardView)

    }

}