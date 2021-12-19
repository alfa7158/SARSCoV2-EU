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
import com.example.covideu.databinding.FragmentShowNUSADataBinding
import com.example.covideu.databinding.FragmentShowSUSADataBinding
import com.example.covideu.model.getAllNorthernAmericanCountries.getAllNorthernAmericanCountriesModel
import com.example.covideu.model.getAllSouthernAmericanCountries.getAllSouthernAmericanCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.n_usa_viewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.s_usa_ViewModel
import com.example.covideu.view.adapter.countriesRecyclers.show_n_usa_dataReyclerView
import com.example.covideu.view.adapter.countriesRecyclers.show_s_usa_DataRecyclerView


class ShowS_USA_DataFragment : Fragment() {
    private val covidDViewModel: s_usa_ViewModel by activityViewModels()
    private lateinit var binding: FragmentShowSUSADataBinding
    private lateinit var show_S_UsaDatAapter: show_s_usa_DataRecyclerView
    private val countriesDataList_N_usa = mutableListOf<getAllSouthernAmericanCountriesModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowSUSADataBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        show_S_UsaDatAapter = show_s_usa_DataRecyclerView(countriesDataList_N_usa)

        binding.sUSARecyclerViiew.adapter =show_S_UsaDatAapter

        observe_N_USA()

        covidDViewModel.callCovidDataForSouthAmerican()





    }

    @SuppressLint("NotifyDataSetChanged")
    fun observe_N_USA(){
        covidDViewModel.covid19SouthAmericaLiveData .observe(viewLifecycleOwner,{
            Log.d("here I am",it.toString())
            countriesDataList_N_usa.addAll(it)

            show_S_UsaDatAapter.notifyDataSetChanged()


        })
    }

}