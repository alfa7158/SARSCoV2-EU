package com.example.covideu.view.Selection.Vaccine

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.databinding.FragmentPhaseFourBinding
import com.example.covideu.databinding.FragmentShowAsiaDataBinding
import com.example.covideu.databinding.FragmentShowEuropDataBinding
import com.example.covideu.databinding.FragmentShowPhaseOneBinding
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_one_vaccines
import com.example.covideu.model.getAllAsianCountries.getAll_AsianCountriesModel
import com.example.covideu.view.ViewModels.countriesDataViewModels.asiaViewModel
import com.example.covideu.view.ViewModels.t_v_ViewModel.CovidT_V_ViewModel
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseOneViewModel
import com.example.covideu.view.adapter.countriesRecyclers.showAsiaDataRecyclerView
import com.example.covideu.view.adapter.vaccine.phaseOneRecyclerView
import java.lang.Exception


class showPhaseOneFragment : Fragment() {
    private val covidDViewModel: phaseOneViewModel by activityViewModels()
    private lateinit var binding: FragmentShowPhaseOneBinding
    private lateinit var phaseOneAdapter: phaseOneRecyclerView
    private var phaseOneDataList = listOf<getPhase_one_vaccines>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowPhaseOneBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phaseOneAdapter = phaseOneRecyclerView(covidDViewModel)

        binding.phaseOneRecyclerView.adapter =phaseOneAdapter

        observePhaseOne()
        covidDViewModel.callPhaseOne()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observePhaseOne(){
        try {
            covidDViewModel.covid19PhaseOneLiveData .observe(viewLifecycleOwner,{
                it?.let {

                    Log.d("here I am",it.toString())
                    binding.progressBarPhaseOne .visibility = View.VISIBLE

                    phaseOneAdapter.submitList(it)

                    phaseOneDataList = it
                    binding.progressBarPhaseOne .visibility = View.GONE

                }



            })

        }catch (e:Exception){

            checkForError()
        }

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()

        requireActivity().menuInflater.inflate(R.menu.custom_menu, menu)
        val searchView = menu.findItem(R.id.searchAction)


        val item_searchView = searchView.actionView as SearchView

        item_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                phaseOneAdapter.submitList(phaseOneDataList.filter {
                    it.trimedName.lowercase().contains(query!!.lowercase())||
                            it.trimedCategory.lowercase().contains(query!!.lowercase())
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
                phaseOneAdapter.submitList(phaseOneDataList)
                return true
            }


        })


    }

    fun checkForSuccessful(){
        covidDViewModel.covid19PhaseOneLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })

    }
    fun checkForError(){

        covidDViewModel.covid19PhaseOneLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })
    }




}








