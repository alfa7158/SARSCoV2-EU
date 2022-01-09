package com.example.covideu.view.Selection.countries

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.view.ViewModels.countriesDataViewModels.s_usa_ViewModel


class fragment_s_usa_details : Fragment() {

    private val covidDViewModel: s_usa_ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_s_usa_details, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val infoRank: TextView = view.findViewById(R.id.s_usa_InfoRank)
        val infCountry: TextView = view.findViewById(R.id.s_usa_CountryInfo)
        val infoDeath: TextView = view.findViewById(R.id.s_usaTotalDeathsInfo)
        val infoTotalCases: TextView = view.findViewById(R.id.s_usa_TotalCasesInfo)
        val populationInfo: TextView = view.findViewById(R.id.s_usa_PopulationInfo)
        val continentInfo: TextView = view.findViewById(R.id.s_usa_ContinentInfo)
        val totalTest: TextView = view.findViewById(R.id.s_usa_Test_Total_Info)
        val activeCasesInfo: TextView = view.findViewById(R.id.s_usa_ActiveCasesInfo)



        covidDViewModel.covid19SouthAmericaLiveDataDetails.observe(viewLifecycleOwner,{
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