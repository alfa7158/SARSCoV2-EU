package com.example.covideu.view.Selection.countries

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.databinding.FragmentShowAUDataFragmentBinding
import com.example.covideu.databinding.FragmentShowAfricaDataFagmentBinding
import com.example.covideu.databinding.FragmentShowAsiaDataBinding
import com.example.covideu.model.getAllAsianCountries.getAll_AsianCountriesModel
import com.example.covideu.model.getAllAustralianAndOceaniancounties.getAllAustralianAndOceanianCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.asiaViewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.auViewModel
import com.example.covideu.view.adapter.countriesRecyclers.showAsiaDataRecyclerView
import com.example.covideu.view.adapter.countriesRecyclers.showAuDataRecyclerView


class ShowAU_DataFragmentFragment : Fragment() {
    private val covidDViewModel: auViewModel by activityViewModels()
    private lateinit var binding: FragmentShowAUDataFragmentBinding
    private lateinit var showAuAdapter: showAuDataRecyclerView
    private val countriesDataListAu = mutableListOf<getAllAustralianAndOceanianCountriesModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowAUDataFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showAuAdapter = showAuDataRecyclerView(countriesDataListAu,covidDViewModel)

        binding.auRecyclerView.adapter =showAuAdapter

        observeAuData()

        covidDViewModel.callCovidDataForAustralianAndOcean()





    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeAuData(){
        covidDViewModel.covid19AustralianAndOceaniaLiveData.observe(viewLifecycleOwner,{
            Log.d("here I am",it.toString())
            countriesDataListAu.addAll(it)

            showAuAdapter.notifyDataSetChanged()


        })
    }





}