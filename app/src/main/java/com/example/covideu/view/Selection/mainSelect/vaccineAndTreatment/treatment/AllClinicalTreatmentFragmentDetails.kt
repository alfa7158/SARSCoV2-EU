package com.example.covideu.view.Selection.mainSelect.vaccineAndTreatment.treatment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.treatment.allClinicalViewModel

/**
 * This class is for all clinical and it is responsible for showing the details of a selected item on a recyclerview
 */

class AllClinicalTreatmentFragmentDetails : Fragment() {

    private val allClinicalViewModel: allClinicalViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        return inflater.inflate(R.layout.fragment_all_clinical_treatment_details, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val researcher: TextView = view.findViewById(R.id.researcherAllClinicalInfo)
        val category: TextView = view.findViewById(R.id.categoryAllClinicalInfo)
        val description: TextView = view.findViewById(R.id.descriptionAllClinicalInfo)
        val ifFdaApproved: TextView = view.findViewById(R.id.fdaApprovedClinical)
        val lastUpdate: TextView = view.findViewById(R.id.lastUpdateAllClinicalInfo)

        allClinicalViewModel.covid19ClinicalLiveDataDetails .observe(viewLifecycleOwner,{

            it?.let {
                researcher.text = it.trimedName
                category.text = "Category:\n${it.trimedCategory}"
                description.text = it.description
                ifFdaApproved.text = "FDA approved:${it.fDAApproved}"
                lastUpdate.text = "Last update:${it.lastUpdated}"


            }

        })


    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }



}