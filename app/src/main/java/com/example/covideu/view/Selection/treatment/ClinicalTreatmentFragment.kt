package com.example.covideu.view.Selection.treatment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.databinding.FragmentAllTreatmentBinding
import com.example.covideu.databinding.FragmentClinicalTreatmentBinding
import com.example.covideu.databinding.FragmentShowAllVaccineBinding
import com.example.covideu.model.VaccineAndTreatments.Treatment.getAllTreatmentsData
import com.example.covideu.model.VaccineAndTreatments.Treatment.getClinicalTreatments
import com.example.covideu.view.ViewModels.t_v_ViewModel.treatment.allClinicalViewModel
import com.example.covideu.view.ViewModels.t_v_ViewModel.treatment.allTreatmentViewModel
import com.example.covideu.view.adapter.treatment.allTreatmentRecyclerView
import com.example.covideu.view.adapter.treatment.showAllClinicalTreatmentRecyclerView


class ClinicalTreatmentFragment : Fragment() {
    private val covidDViewModel: allClinicalViewModel by activityViewModels()
    private lateinit var binding: FragmentClinicalTreatmentBinding
    private lateinit var clinical_treatment_Adapter: showAllClinicalTreatmentRecyclerView
    private val clinical_treatment_DataList = mutableListOf<getClinicalTreatments>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentClinicalTreatmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clinical_treatment_Adapter = showAllClinicalTreatmentRecyclerView(clinical_treatment_DataList,covidDViewModel)

        binding.clinicalTreatmentRecyclerView.adapter =clinical_treatment_Adapter

        observeAllTreatment()
        covidDViewModel.callClinicalLiveData()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observeAllTreatment(){
        covidDViewModel.covid19ClinicalLiveData .observe(viewLifecycleOwner,{
            it?.let {

                Log.d("here I am",it.toString())
                clinical_treatment_DataList.clear()

                clinical_treatment_DataList.addAll(it)

                clinical_treatment_Adapter.notifyDataSetChanged()

                covidDViewModel.covid19ClinicalLiveData.postValue(null)

            }



        })
    }
}