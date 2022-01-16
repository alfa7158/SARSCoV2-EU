package com.example.covideu.view.Selection.countries

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.view.ViewModels.countriesDataViewModels.auViewModel
/**
 * This the Australia detail fragment and its job is to show the selected item
 * details from the recyclerview
 */

class fragment_au_details : Fragment() {
    private val covidDViewModel: auViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_au_details, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val infoRank: TextView = view.findViewById(R.id.auInfoRank)
        val infCountry: TextView = view.findViewById(R.id.auCountryInfo)
        val infoDeath: TextView = view.findViewById(R.id.auTotalDeathsInfo)
        val infoTotalCases: TextView = view.findViewById(R.id.auTotalCasesInfo)
        val populationInfo: TextView = view.findViewById(R.id.auPopulationInfo)
        val continentInfo: TextView = view.findViewById(R.id.auContinentInfo)
        val totalTest: TextView = view.findViewById(R.id.auTest_Total_Info)
        val activeCasesInfo: TextView = view.findViewById(R.id.auActiveCasesInfo)


        /**
         * below we we observe the details that are posted in the
         * covid19AustralianAndOceaniaLiveDataDetails and
         * set values to the text view
         */
        covidDViewModel.covid19AustralianAndOceaniaLiveDataDetails .observe(viewLifecycleOwner,{
        it?.let {
            infoRank.text = "Rank: ${it.rank}"
            infCountry.text = "country name: ${it.country}"
            infoDeath.text = "Total death: ${it.totalDeaths}"
            infoTotalCases.text = "Total cases: ${it.totalCases}"
            populationInfo.text = "Population: ${it.population}"
            continentInfo.text = "continent: ${it.continent}"
            totalTest.text = "Tests Total: ${it.totalTests}"
            activeCasesInfo.text = "Active cases: ${it.activeCases}"

        }










        })

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }
}