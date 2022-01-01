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
import com.example.covideu.databinding.FragmentClinicalTreatmentBinding
import com.example.covideu.databinding.FragmentFDAApprovedTreatmentBinding
import com.example.covideu.model.VaccineAndTreatments.Treatment.getClinicalTreatments
import com.example.covideu.model.VaccineAndTreatments.Treatment.getFDA_Approvedtreatments
import com.example.covideu.view.ViewModels.t_v_ViewModel.treatment.allClinicalViewModel
import com.example.covideu.view.ViewModels.t_v_ViewModel.treatment.allFdaApprovedTreatment
import com.example.covideu.view.adapter.treatment.allTreatmentRecyclerView
import com.example.covideu.view.adapter.treatment.getFDA_approvedTreatment_RecyclerView
import com.example.covideu.view.adapter.treatment.showAllClinicalTreatmentRecyclerView


class FDA_approved_treatmentFragment : Fragment() {
    private val covidDViewModel: allFdaApprovedTreatment by activityViewModels()
    private lateinit var binding: FragmentFDAApprovedTreatmentBinding
    private lateinit var fdaApproved_treatment_Adapter: getFDA_approvedTreatment_RecyclerView
    private val fdaApproved_treatment_DataList = mutableListOf<getFDA_Approvedtreatments>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFDAApprovedTreatmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fdaApproved_treatment_Adapter = getFDA_approvedTreatment_RecyclerView(fdaApproved_treatment_DataList,covidDViewModel)

        binding.approvedTreatmentfdaRecyclerView.adapter =fdaApproved_treatment_Adapter

        observeAllTreatment()
        covidDViewModel.callApprovedTreatmentsLiveData()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observeAllTreatment(){
        covidDViewModel.covid19ApprovedTreatmentsLiveData .observe(viewLifecycleOwner,{
            it?.let {
                Log.d("here I am",it.toString())
                fdaApproved_treatment_DataList.clear()

                fdaApproved_treatment_DataList.addAll(it)

                fdaApproved_treatment_Adapter.notifyDataSetChanged()
                covidDViewModel.covid19ApprovedTreatmentsLiveData.postValue(null)

            }



        })
    }


}