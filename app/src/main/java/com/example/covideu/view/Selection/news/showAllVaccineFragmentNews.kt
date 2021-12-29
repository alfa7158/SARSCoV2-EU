package com.example.covideu.view.Selection.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.covideu.databinding.FragmentShowAllVaccineNewsBinding
import com.example.covideu.model.covidNews.allVaccineNews.allVaccineNews
import com.example.covideu.view.ViewModels.newsViewModels.allVaccineNewsViewModel
import com.example.covideu.view.adapter.newsRecyclers.allVaccineNewsRecyclerView


class showAllVaccineFragment : Fragment() {
    private lateinit var binding: FragmentShowAllVaccineNewsBinding
    private lateinit var showVaccineNewsAdapter: allVaccineNewsRecyclerView
    private var vaccineNewsList = listOf<allVaccineNews>()
    private val covidNewsViewModel: allVaccineNewsViewModel by activityViewModels()

    var thePager =0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowAllVaccineNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showVaccineNewsAdapter = allVaccineNewsRecyclerView(covidNewsViewModel,requireContext())
        binding.allViccineNewsRecyclerView .adapter =showVaccineNewsAdapter
        binding.progressBarVaccinenNews.animate().alpha(0f).setDuration(1000)

        covidNewsViewModel.callAllVaccineNews(thePager)
        observeCovidNews()
        binding.progressBarVaccinenNews.animate().alpha(1f)






    }
    @SuppressLint("NotifyDataSetChanged")
    fun observeCovidNews(){

        covidNewsViewModel.covid19VaccineLiveData.observe(viewLifecycleOwner,{

            Log.d("covidNews",it.toString())
            showVaccineNewsAdapter.submitList(it)
            vaccineNewsList = it
            showVaccineNewsAdapter.notifyDataSetChanged()
        })
    }

}