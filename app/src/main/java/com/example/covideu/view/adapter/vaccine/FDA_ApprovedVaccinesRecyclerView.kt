package com.example.covideu.view.adapter.vaccine

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.example.covideu.R
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getFDA_ApprovedVaccines
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.fdaApprovedVaccineViewModel

class FDA_ApprovedVaccinesRecyclerView(private val list: List<getFDA_ApprovedVaccines>,val viewModel: fdaApprovedVaccineViewModel) :
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

        val item = list[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category

        holder.fdaApprovedVaccineCardView.setOnClickListener {
            it.findNavController().navigate(R.id.action_showFDA_approved_vaccineFragment_to_fdaApprovedVaccineFragmentDetails)
            viewModel.covid19FDAApprovedVaccineLiveDataDetails.postValue(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class FDA_ApprovedVaccinesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val developer: TextView = itemView.findViewById(R.id.developer_researcherAllFdaApproveVaccine)
        val theCategory: TextView = itemView.findViewById(R.id.categoryAllFdaApproveVaccine)
        val fdaApprovedVaccineCardView: CardView = itemView.findViewById(R.id.fdaApproveCardView)

    }

}