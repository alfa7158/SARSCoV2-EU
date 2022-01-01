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
import com.example.covideu.databinding.FragmentShowFDAApprovedVaccineBinding
import com.example.covideu.model.VaccineAndTreatments.Treatment.getAllTreatmentsData
import com.example.covideu.view.ViewModels.t_v_ViewModel.treatment.allTreatmentViewModel
import com.example.covideu.view.adapter.newsRecyclers.allVaccineNewsRecyclerView
import com.example.covideu.view.adapter.treatment.allTreatmentRecyclerView
import com.example.covideu.view.adapter.vaccine.phaseFourRecyclerView


class allTreatmentFragment : Fragment() {
    private val covidDViewModel: allTreatmentViewModel by activityViewModels()
    private lateinit var binding: FragmentAllTreatmentBinding
    private lateinit var all_treatment_Adapter: allTreatmentRecyclerView
    private val all_treatment_DataList = mutableListOf<getAllTreatmentsData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllTreatmentBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        all_treatment_Adapter = allTreatmentRecyclerView(all_treatment_DataList,covidDViewModel)

        binding.allTreatmentRecyclerView.adapter =all_treatment_Adapter

        observeAllTreatment()
        covidDViewModel.callAllCovidTreatment()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observeAllTreatment(){
        covidDViewModel.covidAllTreatmentsLiveData .observe(viewLifecycleOwner,{

            it?.let {
                Log.d("here I am",it.toString())

                all_treatment_DataList.clear()

                all_treatment_DataList.addAll(it)

                all_treatment_Adapter.notifyDataSetChanged()
                covidDViewModel.covidAllTreatmentsLiveData.postValue(null)

            }



        })
    }


}