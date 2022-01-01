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
import com.example.covideu.databinding.FragmentShowAllVaccineBinding
import com.example.covideu.databinding.FragmentShowFDAApprovedVaccineBinding
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getAllVaccinesDataItem
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getFDA_ApprovedVaccines
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.AllVaccineViewModel
import com.example.covideu.view.adapter.vaccine.FDA_ApprovedVaccinesRecyclerView
import com.example.covideu.view.adapter.vaccine.getAllVaccineDataRecyclerView


class showAllVaccineFragment : Fragment() {
    private val covidDViewModel: AllVaccineViewModel by activityViewModels()
    private lateinit var binding: FragmentShowAllVaccineBinding
    private lateinit var all_vaccin_Adapter: getAllVaccineDataRecyclerView
    private val AllVaccine_DataList = mutableListOf<getAllVaccinesDataItem>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowAllVaccineBinding.inflate(inflater,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     all_vaccin_Adapter = getAllVaccineDataRecyclerView(AllVaccine_DataList,covidDViewModel)

        binding.allVaccineRecyclerView.adapter =all_vaccin_Adapter

        observeFDA_approved_v()
        covidDViewModel.callAllVaccinesTreatment()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observeFDA_approved_v(){
        covidDViewModel.covidAllVaccinesLiveData.observe(viewLifecycleOwner,{

            it?.let {
                Log.d("here I am",it.toString())

                AllVaccine_DataList.clear()

                AllVaccine_DataList.addAll(it)

                all_vaccin_Adapter.notifyDataSetChanged()

                covidDViewModel.covidAllVaccinesLiveData.postValue(null)
            }


        })
    }


}