package com.example.covideu.view.Selection.countries

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.covideu.databinding.FragmentShowAfricaDataFagmentBinding
import com.example.covideu.model.getAllAfricanCountries.getAllAfricanCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.africaViewModel
import com.example.covideu.view.adapter.countriesRecyclers.showAfricaDataRecyclerView

private const val TAG = "ShowCountriesDataFagmen"
class ShowCountriesDataFagment : Fragment() {
    private val covidDViewModel:africaViewModel  by activityViewModels()
    private lateinit var binding:FragmentShowAfricaDataFagmentBinding
    private lateinit var showAfricaDatAapter: showAfricaDataRecyclerView
    private val countriesDataListAfrica = mutableListOf<getAllAfricanCountriesModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowAfricaDataFagmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showAfricaDatAapter = showAfricaDataRecyclerView(countriesDataListAfrica)

        binding.africaDataRecyclerView.adapter =showAfricaDatAapter

        observeAfricanData()

        covidDViewModel.callCovidDataForAfrica()







    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeAfricanData(){
        covidDViewModel.covid19AfricaLiveData.observe(viewLifecycleOwner,{
            Log.d("here I am",it.toString())
            countriesDataListAfrica.addAll(it)

            showAfricaDatAapter.notifyDataSetChanged()


        })
    }




}