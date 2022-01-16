package com.example.covideu.view.Selection.countries

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.databinding.FragmentShowAsiaDataBinding
import com.example.covideu.databinding.FragmentShowCountriesWithCovidStatisticalBinding
import com.example.covideu.databinding.FragmentShowNUSADataBinding
import com.example.covideu.model.get.all.covid.Countries.Statistical.getAllCovid19CountriesStatisticalDataItemModel
import com.example.covideu.model.getAllAsianCountries.getAll_AsianCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.asiaViewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.covidStatisticalDataViewModel
import com.example.covideu.view.adapter.countriesRecyclers.showAsiaDataRecyclerView
import com.example.covideu.view.adapter.countriesRecyclers.showCountriesStatisticalDataRecyclerView

/**
 * This class will be used for future features
 */

class showCountriesWithCovidStatisticalFragment : Fragment() {
    private val covidDViewModel: covidStatisticalDataViewModel by activityViewModels()
    private lateinit var binding:FragmentShowCountriesWithCovidStatisticalBinding
    private lateinit var showStatisticalAdapter: showCountriesStatisticalDataRecyclerView
    private val countriesStatisticalDataList = mutableListOf<getAllCovid19CountriesStatisticalDataItemModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        binding = FragmentShowCountriesWithCovidStatisticalBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showStatisticalAdapter = showCountriesStatisticalDataRecyclerView(countriesStatisticalDataList)

        binding.countriesStaticalRecyclerView.adapter =showStatisticalAdapter

        observeAsianData()

        covidDViewModel.callCovidStatisticalData()





    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeAsianData(){
        covidDViewModel.covid19StatisticalLiveData.observe(viewLifecycleOwner,{
            Log.d("here I am",it.toString())
            countriesStatisticalDataList.addAll(it)

            showStatisticalAdapter.notifyDataSetChanged()


        })
    }




}