package com.example.covideu.view.Selection.mainSelect.vaccineAndTreatment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.covideu.R


class ClinicalMainSelectFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        return inflater.inflate(R.layout.fragment_clinical_main_select, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val clinicalTreatmentButton:ImageView = view.findViewById(R.id.allCinicalSelectButton)

        clinicalTreatmentButton.setOnClickListener {


            findNavController().navigate(R.id.action_clinicalMainSelectFragment_to_clinicalTreatmentFragment)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }


}