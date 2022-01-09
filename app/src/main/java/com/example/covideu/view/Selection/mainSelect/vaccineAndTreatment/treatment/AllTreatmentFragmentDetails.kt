package com.example.covideu.view.Selection.mainSelect.vaccineAndTreatment.treatment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.view.ViewModels.t_v_ViewModel.treatment.allClinicalViewModel
import com.example.covideu.view.ViewModels.t_v_ViewModel.treatment.allTreatmentViewModel


class AllTreatmentFragmentDetails : Fragment() {

    private val allTreatmentViewModel: allTreatmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_treatment_details, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val researcher: TextView = view.findViewById(R.id.researcherAllTreatmentInfo)
        val category: TextView = view.findViewById(R.id.categoryAllTreatmentInfo)
        val description: TextView = view.findViewById(R.id.descriptionAllTreatmentInfo)
        val ifFdaApproved: TextView = view.findViewById(R.id.fdaApprovedAllTreatmentInfo)
        val lastUpdate: TextView = view.findViewById(R.id.lastUpdateAllTreatmentInfo)

        allTreatmentViewModel.covidAllTreatmentsLiveDataDetails .observe(viewLifecycleOwner,{

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