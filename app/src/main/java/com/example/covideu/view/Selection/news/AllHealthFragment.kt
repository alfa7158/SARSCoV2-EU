package com.example.covideu.view.Selection.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.databinding.FragmentAllHealthNewsBinding
import com.example.covideu.databinding.FragmentShowAllVaccineNewsBinding
import com.example.covideu.model.covidNews.allHealthNews.AllHeathNewsModel
import com.example.covideu.view.ViewModels.newsViewModels.allHealthNewsViewModel
import com.example.covideu.view.adapter.newsRecyclers.allVaccineNewsRecyclerView
import com.example.covideu.view.adapter.newsRecyclers.showAllHealthNewsRecyclerView


class AllHealthFragment : Fragment() {
    private lateinit var binding: FragmentAllHealthNewsBinding
    private lateinit var showCovidNewsAdapter: showAllHealthNewsRecyclerView
    private val HealthNewsList = mutableListOf<AllHeathNewsModel>()
    private val covidNewsViewModelViewModel: allHealthNewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllHealthNewsBinding.inflate(inflater,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showCovidNewsAdapter = showAllHealthNewsRecyclerView(HealthNewsList)
        binding.allHealthNewsRecyclerView.adapter =showCovidNewsAdapter


        observeCovidNews()
        covidNewsViewModelViewModel.callAllHealthNews("1")






    }
    @SuppressLint("NotifyDataSetChanged")
    fun observeCovidNews(){

        covidNewsViewModelViewModel.covidAllHeathLiveData .observe(viewLifecycleOwner,{

            Log.d("covidNews",it.toString())
            HealthNewsList.addAll(it)

            showCovidNewsAdapter.notifyDataSetChanged()
        })
    }

}