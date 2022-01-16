package com.example.covideu.view.Selection.countries

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.databinding.FragmentShowAUDataFragmentBinding
import com.example.covideu.databinding.FragmentShowAsiaDataBinding
import com.example.covideu.databinding.FragmentShowEuropDataBinding
import com.example.covideu.model.getAllAustralianAndOceaniancounties.getAllAustralianAndOceanianCountriesModel
import com.example.covideu.model.getAllEuropeanCountries.getAllEuropeanCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.auViewModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.europViewModel
import com.example.covideu.view.adapter.countriesRecyclers.showAuDataRecyclerView
import com.example.covideu.view.adapter.countriesRecyclers.showEuropDataRecyclerView
import java.lang.Exception

/**
 * This class holds the the recyclerview for showing the Europe countries
 */

class ShowEuropDataFragment : Fragment() {

    private val covidDViewModel: europViewModel by activityViewModels()
    private lateinit var binding: FragmentShowEuropDataBinding
    private lateinit var showEuropAdapter: showEuropDataRecyclerView
    private var countriesDataListEurope = listOf<getAllEuropeanCountriesModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        binding = FragmentShowEuropDataBinding.inflate(inflater,container,false)
        return binding.root

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showEuropAdapter = showEuropDataRecyclerView(covidDViewModel)

        binding.europRecyclerView.adapter =showEuropAdapter

        observeEurop()

        covidDViewModel.callCovidDataForEurope()





    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeEurop(){

        try {
            covidDViewModel.covid19EuropeLiveData.observe(viewLifecycleOwner,{

                it?.let {
                    binding.eurProgressBar.visibility = View.VISIBLE
                    Log.d("here I am",it.toString())
                    showEuropAdapter.submitList(it)
                    countriesDataListEurope = it
                    binding.eurProgressBar.visibility = View.GONE

                }
                    checkForSuccessful()


            })
        }catch (e:Exception){

            checkForError()
        }

    }
    //////////////////////////search code starts here/////////////////////////////////////

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()

        requireActivity().menuInflater.inflate(R.menu.custom_menu, menu)
        val searchView = menu.findItem(R.id.searchAction)


        val item_searchView = searchView.actionView as SearchView
        /**
         * The item search view is to allow the user to search in the recyclerview by contains and
         * country
         */
        item_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                showEuropAdapter.submitList(countriesDataListEurope.filter {
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
                showEuropAdapter.submitList(countriesDataListEurope)
                return true
            }


        })


    }
    //////////////////////////search code ends here/////////////////////////////////////


    fun checkForSuccessful(){
        covidDViewModel.countryLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })

    }
    fun checkForError(){

        covidDViewModel.CovidLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })
    }




}