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
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getAllVaccinesDataItem
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getFDA_ApprovedVaccines
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_four_vaccines
import com.example.covideu.view.ViewModels.newsViewModels.allVaccineNewsViewModel
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.AllVaccineViewModel

class getAllVaccineDataRecyclerView( val viewModel: AllVaccineViewModel) :
    RecyclerView.Adapter<getAllVaccineDataRecyclerView.getAllVaccineDataViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): getAllVaccineDataRecyclerView.getAllVaccineDataViewHolder {

        return getAllVaccineDataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_all_vaccine_layout,
                parent,
                false
            )
        )
    }
    val DIF_CALLBACK = object : DiffUtil.ItemCallback<getAllVaccinesDataItem>(){
        override fun areItemsTheSame(oldItem: getAllVaccinesDataItem, newItem: getAllVaccinesDataItem): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: getAllVaccinesDataItem, newItem: getAllVaccinesDataItem): Boolean {
            return oldItem==newItem
        }

    }


    private val differ = AsyncListDiffer(this,DIF_CALLBACK)
    override fun onBindViewHolder(holder: getAllVaccineDataViewHolder, position: Int) {

        val item = differ.currentList[position]
        holder.developer.text = item.developerResearcher
        holder.theCategory.text = item.category

        holder.allVaccineCardView.setOnClickListener {
            it.findNavController().navigate(R.id.action_showAllVaccineFragment2_to_allVaccineFragmentDetails)
            viewModel.covidAllVaccinesLiveDataDetails.postValue(item)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list:List<getAllVaccinesDataItem>){
        differ.submitList(list)

    }

    class getAllVaccineDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val developer: TextView = itemView.findViewById(R.id.developer_researcherAllVaccineNews)
        val theCategory: TextView = itemView.findViewById(R.id.categoryAllVaccineTitle)

        val allVaccineCardView: CardView = itemView.findViewById(R.id.allVaccineCardView)

    }

}