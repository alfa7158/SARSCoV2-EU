package com.example.covideu.view.Selection.mainSelect.vaccineAndTreatment.vaccine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseThreeViewModel
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseTwoViewModel

class PhaseThreeFragmentDetails : Fragment() {
    private val covidVaccineViewModel: phaseThreeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phase_three_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val researcher: TextView = view.findViewById(R.id.researcherVaccineInfPhaseThree)
        val category: TextView = view.findViewById(R.id.categoreyVaccineInfoPhaseThree)
        val description: TextView = view.findViewById(R.id.descriptionVaccineInfoPhaseThree)
        val ifFdaApproved: TextView = view.findViewById(R.id.fdaApprovedVaccineInfoPhaseThree)
        val lastUpdate: TextView = view.findViewById(R.id.lastUpdateVaccineInfoPhaseThree)

        covidVaccineViewModel.covid19PhaseThreeLiveDataDetails .observe(viewLifecycleOwner,{

            it?.let {
                researcher.text = it.trimedName
                category.text = "Category:\n${it.trimedCategory}"
                description.text = it.description
                ifFdaApproved.text = "FDA approved:${it.fDAApproved}"
                lastUpdate.text = "Last update:${it.lastUpdated}"



            }








        })


    }


}