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
import com.example.covideu.view.ViewModels.countriesDataViewModels.n_usa_viewModel

class fragment_n_usa_details : Fragment() {
    private val covidDViewModel: n_usa_viewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_n_usa_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val infoRank: TextView = view.findViewById(R.id.nUSARank)
        val infCountry: TextView = view.findViewById(R.id.nUSACountryInfo)
        val infoDeath: TextView = view.findViewById(R.id.nUSATotalDeath)
        val infoTotalCases: TextView = view.findViewById(R.id.nUSATotalCasesInfo)
        val populationInfo: TextView = view.findViewById(R.id.nUSAPopulationInfo)
        val continentInfo: TextView = view.findViewById(R.id.nUSAContinentInfo)
        val totalTest: TextView = view.findViewById(R.id.nUSATest_Total_Info)
        val activeCasesInfo: TextView = view.findViewById(R.id.nUSAActiveCasesInfo)



        covidDViewModel.covid19NorthAmericaLiveData.observe(viewLifecycleOwner,{

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