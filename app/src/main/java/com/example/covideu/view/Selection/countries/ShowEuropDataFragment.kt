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
import com.example.covideu.databinding.FragmentShowAsiaDataBinding
import com.example.covideu.databinding.FragmentShowEuropDataBinding
import com.example.covideu.model.getAllAustralianAndOceaniancounties.getAllAustralianAndOceanianCountriesModel
import com.example.covideu.model.getAllEuropeanCountries.getAllEuropeanCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.auViewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.europViewModel
import com.example.covideu.view.adapter.countriesRecyclers.showAuDataRecyclerView
import com.example.covideu.view.adapter.countriesRecyclers.showEuropDataRecyclerView


class ShowEuropDataFragment : Fragment() {

    private val covidDViewModel: europViewModel by activityViewModels()
    private lateinit var binding: FragmentShowEuropDataBinding
    private lateinit var showEuropAdapter: showEuropDataRecyclerView
    private val countriesDataListEurop = mutableListOf<getAllEuropeanCountriesModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowEuropDataBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showEuropAdapter = showEuropDataRecyclerView(countriesDataListEurop,covidDViewModel)

        binding.europRecyclerView.adapter =showEuropAdapter

        observeEurop()

        covidDViewModel.callCovidDataForEurope()





    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeEurop(){
        covidDViewModel.covid19EuropeLiveData.observe(viewLifecycleOwner,{

            it?.let {
                Log.d("here I am",it.toString())
                countriesDataListEurop.clear()

                countriesDataListEurop.addAll(it)

                showEuropAdapter.notifyDataSetChanged()

                covidDViewModel.covid19EuropeLiveData.postValue(null)

            }



        })
    }


}