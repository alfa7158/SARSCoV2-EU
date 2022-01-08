package com.example.covideu.view.Selection.countries

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.covideu.R
import com.example.covideu.databinding.FragmentShowAfricaDataFagmentBinding
import com.example.covideu.model.getAllAfricanCountries.getAllAfricanCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.africaViewModel
import com.example.covideu.view.adapter.countriesRecyclers.showAfricaDataRecyclerView
import java.util.*

private const val TAG = "ShowCountriesDataFagmen"
class ShowCountriesDataFagment : Fragment() {
    private val covidDViewModel:africaViewModel  by activityViewModels()
    private lateinit var binding:FragmentShowAfricaDataFagmentBinding
    private lateinit var showAfricaDatAapter: showAfricaDataRecyclerView
    private var countriesDataListAfricaShowList = listOf<getAllAfricanCountriesModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        binding = FragmentShowAfricaDataFagmentBinding.inflate(inflater,container,false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        showAfricaDatAapter = showAfricaDataRecyclerView(covidDViewModel)

        binding.africaDataRecyclerView.adapter =showAfricaDatAapter

        observeAfricanData()

        covidDViewModel.callCovidDataForAfrica()







    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeAfricanData(){
        covidDViewModel.covid19AfricaLiveData.observe(viewLifecycleOwner,{

            it?.let {
                binding.africaProgressBar.visibility = View.VISIBLE

                showAfricaDatAapter.submitList(it)
                countriesDataListAfricaShowList = it

                binding.africaProgressBar.visibility = View.GONE


            }





        })
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.custom_menu, menu)
        val searchItem = menu.findItem(R.id.searchAction)


        val itemSearchView = searchItem.actionView as androidx.appcompat.widget.SearchView

        itemSearchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                showAfricaDatAapter.submitList(countriesDataListAfricaShowList.filter {
                    it.country.lowercase().contains(query!!.lowercase())||
                            it.continent.lowercase().contains(query!!.lowercase())
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }


        })
        searchItem.setOnActionExpandListener(object :MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                showAfricaDatAapter.submitList(countriesDataListAfricaShowList)
                return true
            }


        })


    }







}