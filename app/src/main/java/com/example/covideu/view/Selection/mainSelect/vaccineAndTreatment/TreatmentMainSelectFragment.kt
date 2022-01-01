package com.example.covideu.view.Selection.mainSelect.vaccineAndTreatment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.covideu.R
import com.example.covideu.databinding.FragmentTreatmentMainSelectBinding
import com.example.covideu.databinding.FragmentVaccineMainSelectBinding


class TreatmentMainSelectFragment : Fragment() {
    private lateinit var binding: FragmentTreatmentMainSelectBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTreatmentMainSelectBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.allTreatmentSelectButton.setOnClickListener {

            findNavController().navigate(R.id.action_treatmentMainSelectFragment_to_allTreatmentFragment)
        }

        binding.fdaApproveTreatmentselectButton.setOnClickListener {
            findNavController().navigate(R.id.action_treatmentMainSelectFragment_to_FDA_approved_treatmentFragment)

        }


    }


}