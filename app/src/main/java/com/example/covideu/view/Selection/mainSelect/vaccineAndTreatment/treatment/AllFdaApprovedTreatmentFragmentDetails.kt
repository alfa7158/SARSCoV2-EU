package com.example.covideu.view.Selection.mainSelect.vaccineAndTreatment.treatment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.treatment.allFdaApprovedTreatment


class AllFdaApprovedTreatmentFragmentDetails : Fragment() {
    private val allFDATreatmentViewModel: allFdaApprovedTreatment by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_all_fda_approved_treatment_details,
            container,
            false
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val researcher: TextView = view.findViewById(R.id.researcherAllFdaTreatmentInfo)
        val category: TextView = view.findViewById(R.id.categoryAllFdaTreatmentInfo)
        val description: TextView = view.findViewById(R.id.descriptionAllFdaTreatmentInfo)
        val ifFdaApproved: TextView = view.findViewById(R.id.fdaApprovedAllFdaTreatmentInfo)
        val lastUpdate: TextView = view.findViewById(R.id.lastUpdateAllFdaTreatmentInfo)

        allFDATreatmentViewModel.covid19ApprovedTreatmentsLiveDataDetails.observe(viewLifecycleOwner,{

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