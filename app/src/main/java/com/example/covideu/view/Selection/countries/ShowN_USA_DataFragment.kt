package com.example.covideu.view.Selection.countries

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels

import com.example.covideu.databinding.FragmentShowNUSADataBinding
import com.example.covideu.model.getAllNorthernAmericanCountries.getAllNorthernAmericanCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.n_usa_viewModel
import com.example.covideu.view.adapter.countriesRecyclers.show_n_usa_dataReyclerView

class ShowN_USA_DataFragment : Fragment() {
    private val covidDViewModel: n_usa_viewModel by activityViewModels()
    private lateinit var binding: FragmentShowNUSADataBinding
    private lateinit var showN_UsaDatAapter: show_n_usa_dataReyclerView
    private val countriesDataListN_usa = mutableListOf<getAllNorthernAmericanCountriesModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowNUSADataBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showN_UsaDatAapter = show_n_usa_dataReyclerView(countriesDataListN_usa,covidDViewModel)

        binding.NUsaRecyclerView.adapter =showN_UsaDatAapter

        observe_N_USA()

        covidDViewModel.callCovidDataForNorthAmerican()





    }

    @SuppressLint("NotifyDataSetChanged")
    fun observe_N_USA(){
        covidDViewModel.covid19NorthAmericaLiveData.observe(viewLifecycleOwner,{
            Log.d("here I am",it.toString())
            countriesDataListN_usa.addAll(it)

            showN_UsaDatAapter.notifyDataSetChanged()


        })
    }



}