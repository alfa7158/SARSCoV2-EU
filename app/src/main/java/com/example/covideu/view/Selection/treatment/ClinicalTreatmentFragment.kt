package com.example.covideu.view.Selection.treatment

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
import com.example.covideu.databinding.FragmentAllTreatmentBinding
import com.example.covideu.databinding.FragmentClinicalTreatmentBinding
import com.example.covideu.databinding.FragmentShowAllVaccineBinding
import com.example.covideu.model.VaccineAndTreatments.Treatment.getAllTreatmentsData
import com.example.covideu.model.VaccineAndTreatments.Treatment.getClinicalTreatments
import com.example.covideu.view.ViewModels.t_v_ViewModel.treatment.allClinicalViewModel
import com.example.covideu.view.ViewModels.t_v_ViewModel.treatment.allTreatmentViewModel
import com.example.covideu.view.adapter.treatment.allTreatmentRecyclerView
import com.example.covideu.view.adapter.treatment.showAllClinicalTreatmentRecyclerView
import java.lang.Exception


class ClinicalTreatmentFragment : Fragment() {
    private val covidDViewModel: allClinicalViewModel by activityViewModels()
    private lateinit var binding: FragmentClinicalTreatmentBinding
    private lateinit var clinical_treatment_Adapter: showAllClinicalTreatmentRecyclerView
    private var clinical_treatment_DataList = listOf<getClinicalTreatments>()

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
        binding = FragmentClinicalTreatmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clinical_treatment_Adapter = showAllClinicalTreatmentRecyclerView(covidDViewModel)

        binding.clinicalTreatmentRecyclerView.adapter =clinical_treatment_Adapter

        observeAllTreatment()
        covidDViewModel.callClinicalLiveData()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observeAllTreatment(){

        try {
            covidDViewModel.covid19ClinicalLiveData .observe(viewLifecycleOwner,{
                it?.let {
                    binding.clinicalProgressBar.visibility = View.VISIBLE

                    Log.d("here I am",it.toString())
                    clinical_treatment_Adapter.submitList(it)
                    clinical_treatment_DataList = it
                    binding.clinicalProgressBar.visibility = View.GONE

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
                clinical_treatment_Adapter.submitList(clinical_treatment_DataList.filter {
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
                clinical_treatment_Adapter.submitList(clinical_treatment_DataList)
                return true
            }


        })


    }


    fun checkForSuccessful(){
        covidDViewModel.treatmentLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })

    }
    fun checkForError(){

        covidDViewModel.CovidLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })
    }
}