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
import com.example.covideu.databinding.FragmentShowAfricaDataFagmentBinding
import com.example.covideu.databinding.FragmentShowAsiaDataBinding
import com.example.covideu.model.getAllAfricanCountries.getAllAfricanCountriesModel
import com.example.covideu.model.getAllAsianCountries.getAll_AsianCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.africaViewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.asiaViewModel
import com.example.covideu.view.adapter.countriesRecyclers.showAfricaDataRecyclerView
import com.example.covideu.view.adapter.countriesRecyclers.showAsiaDataRecyclerView


class ShowAsiaDataFragment : Fragment() {
    private val covidDViewModel: asiaViewModel by activityViewModels()
    private lateinit var binding:FragmentShowAsiaDataBinding
    private lateinit var showAsiaAdapter: showAsiaDataRecyclerView
    private val countriesDataListAsia = mutableListOf<getAll_AsianCountriesModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowAsiaDataBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showAsiaAdapter = showAsiaDataRecyclerView(countriesDataListAsia,covidDViewModel)

        binding.aisaRecyclerVIew.adapter =showAsiaAdapter

        observeAsianData()

        covidDViewModel.callCovidDataForAsia()





    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeAsianData(){
        covidDViewModel.covid19AsiaLiveData.observe(viewLifecycleOwner,{

            it?.let {
                Log.d("here I am",it.toString())
                countriesDataListAsia.clear()
                countriesDataListAsia.addAll(it)

                showAsiaAdapter.notifyDataSetChanged()

                covidDViewModel.covid19AsiaLiveData .postValue(null)


            }




        })
    }


}