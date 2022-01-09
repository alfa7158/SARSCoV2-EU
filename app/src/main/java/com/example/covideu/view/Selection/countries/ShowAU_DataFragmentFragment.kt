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
import com.example.covideu.databinding.FragmentShowAUDataFragmentBinding
import com.example.covideu.databinding.FragmentShowAfricaDataFagmentBinding
import com.example.covideu.databinding.FragmentShowAsiaDataBinding
import com.example.covideu.model.getAllAsianCountries.getAll_AsianCountriesModel
import com.example.covideu.model.getAllAustralianAndOceaniancounties.getAllAustralianAndOceanianCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.asiaViewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.auViewModel
import com.example.covideu.view.adapter.countriesRecyclers.showAsiaDataRecyclerView
import com.example.covideu.view.adapter.countriesRecyclers.showAuDataRecyclerView


class ShowAU_DataFragmentFragment : Fragment() {
    private val covidDViewModel: auViewModel by activityViewModels()
    private lateinit var binding: FragmentShowAUDataFragmentBinding
    private lateinit var showAuAdapter: showAuDataRecyclerView
    private var countriesDataListAu = listOf<getAllAustralianAndOceanianCountriesModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        binding = FragmentShowAUDataFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showAuAdapter = showAuDataRecyclerView(covidDViewModel)

        binding.auRecyclerView.adapter =showAuAdapter

        observeAuData()

        covidDViewModel.callCovidDataForAustralianAndOcean()





    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeAuData(){
        covidDViewModel.covid19AustralianAndOceaniaLiveData.observe(viewLifecycleOwner,{
        it?.let {
            binding.auUsaProgressBar.visibility = View.VISIBLE

            Log.d("here I am",it.toString())
            showAuAdapter.submitList(it)
            countriesDataListAu = it

            binding.auUsaProgressBar.visibility = View.GONE

        }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()

        requireActivity().menuInflater.inflate(R.menu.custom_menu, menu)
        val searchView = menu.findItem(R.id.searchAction)


        val item_searchView = searchView.actionView as SearchView

        item_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                showAuAdapter.submitList(countriesDataListAu.filter {
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
                showAuAdapter.submitList(countriesDataListAu)
                return true
            }


        })


    }





}