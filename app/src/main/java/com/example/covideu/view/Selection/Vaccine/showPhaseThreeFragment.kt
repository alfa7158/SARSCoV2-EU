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
import com.example.covideu.databinding.FragmentShowPhaseThreeBinding
import com.example.covideu.databinding.FragmentShowPhaseTwoBinding
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_three_vaccines
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_two_vaccines
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseThreeViewModel
import com.example.covideu.view.adapter.vaccine.phaseThreeRecyclerView
import com.example.covideu.view.adapter.vaccine.phaseTwoRecyclerView
import java.lang.Exception


class showPhaseThreeFragment : Fragment() {
    private val covidDViewModel: phaseThreeViewModel by activityViewModels()
    private lateinit var binding: FragmentShowPhaseThreeBinding
    private lateinit var phaseThreeAdapter: phaseThreeRecyclerView
    private var phaseThreeDataList = listOf<getPhase_three_vaccines>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowPhaseThreeBinding.inflate(inflater,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phaseThreeAdapter = phaseThreeRecyclerView(covidDViewModel)

        binding.phaseThreeRecyclerView.adapter =phaseThreeAdapter

        observePhaseThree()
        covidDViewModel.callPhaseThree()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observePhaseThree(){

        try {
            covidDViewModel.covid19PhaseThreeLiveData.observe(viewLifecycleOwner,{
                it?.let {
                    binding.progressBarPhaseThree .visibility = View.VISIBLE

                    Log.d("here I am",it.toString())
                    phaseThreeAdapter.submitList(it)
                    phaseThreeDataList = it
                    binding.progressBarPhaseThree .visibility = View.GONE

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
                phaseThreeAdapter.submitList(phaseThreeDataList.filter {
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
                phaseThreeAdapter.submitList(phaseThreeDataList)
                return true
            }


        })


    }

    fun checkForSuccessful(){
        covidDViewModel.covid19PhaseThreeLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })

    }
    fun checkForError(){

        covidDViewModel.covid19PhaseThreeLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })
    }

}