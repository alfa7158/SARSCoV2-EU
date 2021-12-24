package com.example.covideu.view.Selection.countries

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.view.ViewModels.countriesDataViewModels.auViewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.europViewModel


class fragment_europe_details : Fragment() {
    private val covidDViewModel: europViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_europe_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val infoRank: TextView = view.findViewById(R.id.europeInfoRank)
        val infCountry: TextView = view.findViewById(R.id.europeCountryInfo)
        val infoDeath: TextView = view.findViewById(R.id.europeTotalDeathsInfo)
        val infoTotalCases: TextView = view.findViewById(R.id.europeTotalCasesInfo)
        val populationInfo: TextView = view.findViewById(R.id.europePopulationInfo)
        val continentInfo: TextView = view.findViewById(R.id.europeContinentInfo)
        val totalTest: TextView = view.findViewById(R.id.europeTest_Total_Info)
        val activeCasesInfo: TextView = view.findViewById(R.id.europeActiveCasesInfo)



        covidDViewModel.covid19EuropeLiveData.observe(viewLifecycleOwner,{

            infoRank.text = "Rank: ${it[0].rank}"
            infCountry.text = "country name: ${it[0].country}"
            infoDeath.text = "Total death: ${it[0].totalDeaths}"
            infoTotalCases.text = "Total cases: ${it[0].totalCases}"
            populationInfo.text = "Population: ${it[0].population}"
            continentInfo.text = "continent: ${it[0].continent}"
            totalTest.text = "Tests Total: ${it[0].totalTests}"
            activeCasesInfo.text = "Active cases: ${it[0].activeCases}"




        })



    }


}