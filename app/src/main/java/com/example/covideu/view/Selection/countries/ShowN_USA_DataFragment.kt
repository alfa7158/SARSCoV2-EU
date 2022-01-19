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

import com.example.covideu.databinding.FragmentShowNUSADataBinding
import com.example.covideu.model.getAllNorthernAmericanCountries.getAllNorthernAmericanCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.n_usa_viewModel
import com.example.covideu.view.adapter.countriesRecyclers.show_n_usa_dataReyclerView
import java.lang.Exception


/**
 * This class holds the the recyclerview for showing the North USA countries
 */
class ShowN_USA_DataFragment : Fragment() {
    private val covidDViewModel: n_usa_viewModel by activityViewModels()
    private lateinit var binding: FragmentShowNUSADataBinding
    private lateinit var showN_UsaDatAapter: show_n_usa_dataReyclerView
    private var countriesDataListN_usa = listOf<getAllNorthernAmericanCountriesModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        binding = FragmentShowNUSADataBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showN_UsaDatAapter = show_n_usa_dataReyclerView(covidDViewModel,requireContext())

        binding.NUsaRecyclerView.adapter =showN_UsaDatAapter

        observe_N_USA()

        covidDViewModel.callCovidDataForNorthAmerican()





    }

    @SuppressLint("NotifyDataSetChanged")
    fun observe_N_USA(){

            try {
                covidDViewModel.covid19NorthAmericaLiveData.observe(viewLifecycleOwner,{
                    it?.let {
                        binding.NUsaProgressBar.visibility = View.VISIBLE

                        Log.d("here I am",it.toString())
                        showN_UsaDatAapter.submitList(it)
                        countriesDataListN_usa = it
                        binding.NUsaProgressBar.visibility = View.GONE

                    }

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
                showN_UsaDatAapter.submitList(countriesDataListN_usa.filter {
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
                showN_UsaDatAapter.submitList(countriesDataListN_usa)
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