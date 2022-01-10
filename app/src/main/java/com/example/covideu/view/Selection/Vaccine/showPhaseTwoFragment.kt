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
import com.example.covideu.databinding.FragmentShowPhaseOneBinding
import com.example.covideu.databinding.FragmentShowPhaseTwoBinding
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_one_vaccines
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_two_vaccines

import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseTwoViewModel
import com.example.covideu.view.adapter.vaccine.phaseOneRecyclerView
import com.example.covideu.view.adapter.vaccine.phaseTwoRecyclerView
import java.lang.Exception


class showPhaseTwoFragment : Fragment() {
    private val covidDViewModel: phaseTwoViewModel by activityViewModels()
    private lateinit var binding: FragmentShowPhaseTwoBinding
    private lateinit var phaseTwoAdapter: phaseTwoRecyclerView
    private var phaseTwoDataList = listOf<getPhase_two_vaccines>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowPhaseTwoBinding.inflate(inflater,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phaseTwoAdapter = phaseTwoRecyclerView(covidDViewModel)

        binding.phaseTwoRecyclerView.adapter =phaseTwoAdapter

        observePhaseTwo()
        covidDViewModel.callPhaseTwo()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observePhaseTwo(){

        try {
            covidDViewModel.covid19PhaseTwoLiveData.observe(viewLifecycleOwner,{
                it?.let {
                    binding.progressBarPhaseTwo .visibility = View.VISIBLE

                    phaseTwoAdapter.submitList(it)
                    phaseTwoDataList = it
                    binding.progressBarPhaseTwo .visibility = View.VISIBLE

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
                phaseTwoAdapter.submitList(phaseTwoDataList.filter {
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
                phaseTwoAdapter.submitList(phaseTwoDataList)
                return true
            }


        })


    }

    fun checkForSuccessful(){
        covidDViewModel.covid19PhaseTwoLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })

    }
    fun checkForError(){

        covidDViewModel.covid19PhaseTwoLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })
    }




}