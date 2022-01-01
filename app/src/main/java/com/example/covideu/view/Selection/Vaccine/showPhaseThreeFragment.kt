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
import com.example.covideu.databinding.FragmentShowPhaseThreeBinding
import com.example.covideu.databinding.FragmentShowPhaseTwoBinding
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_three_vaccines
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_two_vaccines
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseThreeViewModel
import com.example.covideu.view.adapter.vaccine.phaseThreeRecyclerView
import com.example.covideu.view.adapter.vaccine.phaseTwoRecyclerView


class showPhaseThreeFragment : Fragment() {
    private val covidDViewModel: phaseThreeViewModel by activityViewModels()
    private lateinit var binding: FragmentShowPhaseThreeBinding
    private lateinit var phaseThreeAdapter: phaseThreeRecyclerView
    private val phaseThreeDataList = mutableListOf<getPhase_three_vaccines>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowPhaseThreeBinding.inflate(inflater,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phaseThreeAdapter = phaseThreeRecyclerView(phaseThreeDataList,covidDViewModel)

        binding.phaseThreeRecyclerView.adapter =phaseThreeAdapter

        observePhaseThree()
        covidDViewModel.callPhaseThree()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observePhaseThree(){
        covidDViewModel.covid19PhaseThreeLiveData.observe(viewLifecycleOwner,{
            it?.let {

                Log.d("here I am",it.toString())
                phaseThreeDataList.clear()
                phaseThreeDataList.addAll(it)
                phaseThreeAdapter.notifyDataSetChanged()
                covidDViewModel.covid19PhaseThreeLiveData.postValue(null)
            }



        })
    }


}