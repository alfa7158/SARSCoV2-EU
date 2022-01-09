package com.example.covideu.view.Selection.mainSelect.vaccineAndTreatment.vaccine

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.covideu.R
import com.example.covideu.databinding.FragmentMainSelectBinding
import com.example.covideu.databinding.FragmentVaccineMainSelectBinding


class VaccineMainSelectFragment : Fragment() {

    private lateinit var binding: FragmentVaccineMainSelectBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVaccineMainSelectBinding.inflate(inflater,container,false)
        return binding.root

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.phaseOneSelect.setOnClickListener {

            findNavController().navigate(R.id.action_vaccineMainSelectFragment_to_showPhaseOneFragment)


        }

        binding.phaseTwoSelect.setOnClickListener {
            findNavController().navigate(R.id.action_vaccineMainSelectFragment_to_showPhaseTwoFragment)

        }

        binding.phaseThreeSelect.setOnClickListener {

            findNavController().navigate(R.id.action_vaccineMainSelectFragment_to_showPhaseThreeFragment)
        }

        binding.phaseFourSelect.setOnClickListener {

            findNavController().navigate(R.id.action_vaccineMainSelectFragment_to_phaseFourFragment)
        }
        binding.fdaApprovedVaccineSelect.setOnClickListener {
            findNavController().navigate(R.id.action_vaccineMainSelectFragment_to_showFDA_approved_vaccineFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }



}