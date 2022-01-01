package com.example.covideu.view.Selection.mainSelect.vaccineAndTreatment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.covideu.R


class SecVaccineAndTreatmentFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vaccine_and_treatment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val treatmentButton:ImageView = view.findViewById(R.id.TreatmentMainSelectButton)
        val vaccineButton:ImageView = view.findViewById(R.id.VaccineMainSelectButton)
        val clinicalButton:ImageView = view.findViewById(R.id.clinicalMainSelectButton)


        treatmentButton.setOnClickListener {

            findNavController().navigate(R.id.action_secVaccineAndTreatmentFragment_to_treatmentMainSelectFragment)
        }



        vaccineButton.setOnClickListener {

            findNavController().navigate(R.id.action_secVaccineAndTreatmentFragment_to_vaccineMainSelectFragment)
        }

        clinicalButton.setOnClickListener {

            findNavController().navigate(R.id.action_secVaccineAndTreatmentFragment_to_clinicalMainSelectFragment)
        }









    }
}