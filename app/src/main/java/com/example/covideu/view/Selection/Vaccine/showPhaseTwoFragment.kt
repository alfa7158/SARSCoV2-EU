package com.example.covideu.view.Selection.Vaccine

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.databinding.FragmentShowPhaseOneBinding
import com.example.covideu.databinding.FragmentShowPhaseTwoBinding
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_one_vaccines
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_two_vaccines

import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseTwoViewModel
import com.example.covideu.view.adapter.vaccine.phaseOneRecyclerView
import com.example.covideu.view.adapter.vaccine.phaseTwoRecyclerView


class showPhaseTwoFragment : Fragment() {
    private val covidDViewModel: phaseTwoViewModel by activityViewModels()
    private lateinit var binding: FragmentShowPhaseTwoBinding
    private lateinit var phaseTwoAdapter: phaseTwoRecyclerView
    private val phaseTwoDataList = mutableListOf<getPhase_two_vaccines>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowPhaseTwoBinding.inflate(inflater,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phaseTwoAdapter = phaseTwoRecyclerView(phaseTwoDataList,covidDViewModel)

        binding.phaseTwoRecyclerView.adapter =phaseTwoAdapter

        observePhaseTwo()
        covidDViewModel.callPhaseTwo()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observePhaseTwo(){

        covidDViewModel.covid19PhaseTwoLiveData.observe(viewLifecycleOwner,{
            it?.let {
                Log.d("here I am",it.toString())
                phaseTwoDataList.clear()

                phaseTwoDataList.addAll(it)

                phaseTwoAdapter.notifyDataSetChanged()

                covidDViewModel.covid19PhaseTwoLiveData .postValue(null)

            }


        })
    }






}