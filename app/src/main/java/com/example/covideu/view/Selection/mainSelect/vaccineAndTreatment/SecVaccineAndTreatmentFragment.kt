package com.example.covideu.view.Selection.mainSelect.vaccineAndTreatment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.covideu.R

/**
 * This class is responsible for manging the navigation from the main select for vaccine and
 * treatments
 */
class SecVaccineAndTreatmentFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        return inflater.inflate(R.layout.fragment_vaccine_and_treatment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }

}