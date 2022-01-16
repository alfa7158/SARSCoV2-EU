package com.example.covideu.view.Selection.mainSelect.news

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.covideu.R

/**
 * This class is the main select class which is responsible for holding the navigation for the news
 * selection including all health news, all vaccine news and all covid-19 news
 */
class newsMainSelectFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        return inflater.inflate(R.layout.fragment_news_main_select, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

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
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }


}