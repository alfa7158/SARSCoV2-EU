package com.example.covideu.view.Selection.Vaccine

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.covideu.databinding.FragmentShowFDAApprovedVaccineBinding
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getFDA_ApprovedVaccines
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.fdaApprovedVaccineViewModel
import com.example.covideu.view.adapter.vaccine.FDA_ApprovedVaccinesRecyclerView


class showFDA_approved_vaccineFragment : Fragment() {
    private val covidDViewModel: fdaApprovedVaccineViewModel by activityViewModels()
    private lateinit var binding: FragmentShowFDAApprovedVaccineBinding
    private lateinit var FDA_approved_vaccin_Adapter: FDA_ApprovedVaccinesRecyclerView
    private val FDA_approved_vaccine_DataList = mutableListOf<getFDA_ApprovedVaccines>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowFDAApprovedVaccineBinding.inflate(inflater,container,false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FDA_approved_vaccin_Adapter = FDA_ApprovedVaccinesRecyclerView(FDA_approved_vaccine_DataList,covidDViewModel)

        binding.allFDAApprovedVaccineRecyclerView.adapter =FDA_approved_vaccin_Adapter

        observeFDA_approved()
        covidDViewModel.callFdaAllVaccinesTreatment()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observeFDA_approved(){
        covidDViewModel.covid19FDAApprovedVaccineLiveData.observe(viewLifecycleOwner,{
            it?.let {

                Log.d("here I am",it.toString())

                FDA_approved_vaccine_DataList.clear()

                FDA_approved_vaccine_DataList.addAll(it)

                FDA_approved_vaccin_Adapter.notifyDataSetChanged()

                covidDViewModel.covid19FDAApprovedVaccineLiveData.postValue(null)
            }


        })
    }

}