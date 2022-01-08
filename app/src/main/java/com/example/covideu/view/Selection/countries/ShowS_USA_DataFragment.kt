package com.example.covideu.view.Selection.countries

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
    private var countriesDataList_N_usa = listOf<getAllSouthernAmericanCountriesModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        //(activity as AppCompatActivity?)!!.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        // Inflate the layout for this fragment
        binding = FragmentShowSUSADataBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        show_S_UsaDatAapter = show_s_usa_DataRecyclerView(covidDViewModel)

        binding.sUSARecyclerViiew.adapter =show_S_UsaDatAapter

        observe_N_USA()

        covidDViewModel.callCovidDataForSouthAmerican()





    }

    @SuppressLint("NotifyDataSetChanged")
    fun observe_N_USA(){

        covidDViewModel.covid19SouthAmericaLiveData .observe(viewLifecycleOwner,{
            it?.let {
                Log.d("here I am",it.toString())
                binding.SUsaProgressBar.visibility = View.VISIBLE

                show_S_UsaDatAapter.submitList(it)
                countriesDataList_N_usa = it
                binding.SUsaProgressBar.visibility = View.GONE

            }




        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.custom_menu, menu)
        val searchView = menu.findItem(R.id.searchAction)


        val item_searchView = searchView.actionView as SearchView

        item_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                show_S_UsaDatAapter.submitList(countriesDataList_N_usa.filter {
                    it.country.lowercase().contains(query!!.lowercase())||
                            it.continent.lowercase().contains(query!!.lowercase())
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }


        })
        searchView.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                show_S_UsaDatAapter.submitList(countriesDataList_N_usa)
                return true
            }


        })


    }

}