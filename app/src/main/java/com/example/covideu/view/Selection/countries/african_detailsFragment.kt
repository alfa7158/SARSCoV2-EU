package com.example.covideu.view.Selection.countries

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import com.example.covideu.R
import com.example.covideu.model.getAllAfricanCountries.getAllAfricanCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.africaViewModel


class african_detailsFragment : Fragment() {
    private val covidDViewModel: africaViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_africa_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val infoRank:TextView = view.findViewById(R.id.africanInfoRank)
        val infCountry:TextView = view.findViewById(R.id.africanCountryInfo)
        val infoDeath:TextView = view.findViewById(R.id.africanTotalDeathsInfo)
        val totalCases:TextView = view.findViewById(R.id.africTotalCasesInfo)
        val populationInfo:TextView = view.findViewById(R.id.africanPopulationInfo)
        val continentInfo:TextView = view.findViewById(R.id.africanContinentInfo)
        val totalTest:TextView = view.findViewById(R.id.africaTest_Total_Info)
        val activeCasesInfo:TextView = view.findViewById(R.id.africaActiveCasesInfo)

        covidDViewModel.covid19AfricaLiveDataDetails.observe(viewLifecycleOwner,{

            it?.let {
                infoRank.text = "Rank: ${it.rank}"
                infCountry.text = "country name: ${it.country}"
                infoDeath.text = "Total death: ${it.totalDeaths}"
                totalCases.text = "Total cases: ${it.totalCases}"
                populationInfo.text = "Population: ${it.population}"
                continentInfo.text = "continent: ${it.continent}"
                totalTest.text = "Tests Total: ${it.totalTests}"
                activeCasesInfo.text = "Active cases: ${it.activeCases}"


            }








        })


    }






}