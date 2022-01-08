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
import com.example.covideu.model.getAllEuropeanCountries.getAllEuropeanCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.s_usa_ViewModel
import com.example.covideu.view.ViewModels.t_v_ViewModel.treatment.allTreatmentViewModel

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