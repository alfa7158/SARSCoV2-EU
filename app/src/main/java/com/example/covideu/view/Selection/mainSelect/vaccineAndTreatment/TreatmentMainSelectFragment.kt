package com.example.covideu.view.Selection.mainSelect.vaccineAndTreatment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
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
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        binding = FragmentTreatmentMainSelectBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

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
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }



}