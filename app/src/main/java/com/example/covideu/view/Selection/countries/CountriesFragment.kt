package com.example.covideu.view.Selection.countries

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.covideu.R


class CountriesFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val africanCountry:ImageView = view.findViewById(R.id.africaButton)
        val asianCountry:ImageView = view.findViewById(R.id.AsiaButton)
        val nUsaButton:ImageView = view.findViewById(R.id.nUSAButton)
        val sUsaButton:ImageView = view.findViewById(R.id.SUSAButton)
        val europeButton:ImageView = view.findViewById(R.id.europButton)
        val AuButton:ImageView = view.findViewById(R.id.AuButton)

        africanCountry.setOnClickListener {
            findNavController().navigate(R.id.action_countriesFragment2_to_showCountriesDataFagment)
        }
        asianCountry.setOnClickListener{
            findNavController().navigate(R.id.action_countriesFragment2_to_showAsiaDataFragment)

        }

        nUsaButton.setOnClickListener {
            findNavController().navigate(R.id.action_countriesFragment2_to_showN_USA_DataFragment)
        }

        sUsaButton.setOnClickListener {
            findNavController().navigate(R.id.action_countriesFragment2_to_showS_USA_DataFragment2)
        }

        europeButton.setOnClickListener {
            findNavController().navigate(R.id.action_countriesFragment2_to_showEuropDataFragment)
        }


        AuButton.setOnClickListener {
        findNavController().navigate(R.id.action_countriesFragment2_to_showAU_DataFragmentFragment)
        }







    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }





    }
