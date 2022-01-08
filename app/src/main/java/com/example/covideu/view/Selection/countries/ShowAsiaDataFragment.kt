package com.example.covideu.view.Selection.countries

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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
    private var countriesDataListAsia = listOf<getAll_AsianCountriesModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        binding = FragmentShowAsiaDataBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showAsiaAdapter = showAsiaDataRecyclerView(covidDViewModel)

        binding.aisaRecyclerVIew.adapter =showAsiaAdapter

        observeAsianData()

        covidDViewModel.callCovidDataForAsia()





    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeAsianData(){
        covidDViewModel.covid19AsiaLiveData.observe(viewLifecycleOwner,{

            it?.let {
                Log.d("here I am",it.toString())
                binding.asiaprogressBar.visibility = View.VISIBLE
                showAsiaAdapter.submitList(it)
                countriesDataListAsia = it

                binding.asiaprogressBar.visibility = View.GONE


            }




        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.custom_menu, menu)
        val searchView = menu.findItem(R.id.searchAction)


        val item_searchView = searchView.actionView as SearchView

        item_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                showAsiaAdapter.submitList(countriesDataListAsia.filter {
                    it.country.lowercase().contains(query!!.lowercase())||
                            it.continent.lowercase().contains(query!!.lowercase())
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }


        })
        searchView.setOnActionExpandListener(object :MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                showAsiaAdapter.submitList(countriesDataListAsia)
                return true
            }


        })


    }

}