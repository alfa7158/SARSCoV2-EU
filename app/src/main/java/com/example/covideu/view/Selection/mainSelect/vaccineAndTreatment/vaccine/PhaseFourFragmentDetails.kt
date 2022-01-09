package com.example.covideu.view.Selection.mainSelect.vaccineAndTreatment.vaccine

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseFourViewModel
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseTwoViewModel

class PhaseFourFragmentDetails : Fragment() {
    private val covidVaccineViewModel: phaseFourViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phase_four_details, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val researcher: TextView = view.findViewById(R.id.researcherVaccineInfPhaseFour)
        val category: TextView = view.findViewById(R.id.categoreyVaccineInfoPhaseFour)
        val description: TextView = view.findViewById(R.id.descriptionVaccineInfoPhaseFour)
        val ifFdaApproved: TextView = view.findViewById(R.id.fdaApprovedVaccineInfoPhaseFour)
        val lastUpdate: TextView = view.findViewById(R.id.lastUpdateVaccineInfoPhaseFour)

        covidVaccineViewModel.covid19PhaseFourLiveDataDetails.observe(viewLifecycleOwner,{

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