package com.example.covideu.view.Selection.mainSelect.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.covideu.R

class newsMainSelectFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_main_select, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val covidNews:ImageView =  view.findViewById(R.id.covidNewsMainSelect)
       val healthNews:ImageView =  view.findViewById(R.id.healthNewsMainSelect)
       val vaccineNews:ImageView =  view.findViewById(R.id.vaccineNewsMainSelect)


        covidNews.setOnClickListener {
            findNavController().navigate(R.id.action_newsMainSelectFragment_to_showCovidNewsFragment2)
        }

        healthNews.setOnClickListener {
            findNavController().navigate(R.id.action_newsMainSelectFragment_to_allHealthFragment)

        }
        vaccineNews.setOnClickListener {
            findNavController().navigate(R.id.action_newsMainSelectFragment_to_showAllVaccineFragment)

        }


    }


}