package com.example.covideu.view.Covid19Check

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.covideu.R
import com.example.covideu.databinding.FragmentCheckForCovidBinding
import com.example.covideu.databinding.FragmentFetchVideosBinding
import com.example.covideu.databinding.FragmentMainSelectBinding


class Check_for_covid_Fragment : Fragment() {
    lateinit var binding: FragmentCheckForCovidBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckForCovidBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }


}