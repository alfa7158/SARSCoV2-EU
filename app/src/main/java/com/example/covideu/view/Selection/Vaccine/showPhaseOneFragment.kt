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
import com.example.covideu.databinding.FragmentPhaseFourBinding
import com.example.covideu.databinding.FragmentShowAsiaDataBinding
import com.example.covideu.databinding.FragmentShowEuropDataBinding
import com.example.covideu.databinding.FragmentShowPhaseOneBinding
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_one_vaccines
import com.example.covideu.model.getAllAsianCountries.getAll_AsianCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.asiaViewModel
import com.example.covideu.view.ViewModels.t_v_ViewModel.CovidT_V_ViewModel
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseOneViewModel
import com.example.covideu.view.adapter.countriesRecyclers.showAsiaDataRecyclerView
import com.example.covideu.view.adapter.vaccine.phaseOneRecyclerView



class showPhaseOneFragment : Fragment() {
    private val covidDViewModel: phaseOneViewModel by activityViewModels()
    private lateinit var binding: FragmentShowPhaseOneBinding
    private lateinit var phaseOneAdapter: phaseOneRecyclerView
    private val phaseOneDataList = mutableListOf<getPhase_one_vaccines>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowPhaseOneBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phaseOneAdapter = phaseOneRecyclerView(phaseOneDataList)

        binding.phaseOneRecyclerView.adapter =phaseOneAdapter

        observePhaseOne()
        covidDViewModel.callPhaseOne()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observePhaseOne(){
        covidDViewModel.covid19PhaseOneLiveData .observe(viewLifecycleOwner,{
            Log.d("here I am",it.toString())
            phaseOneDataList.addAll(it)

            phaseOneAdapter.notifyDataSetChanged()


        })
    }




}








