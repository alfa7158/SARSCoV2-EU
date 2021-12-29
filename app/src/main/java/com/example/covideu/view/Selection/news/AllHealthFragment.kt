package com.example.covideu.view.Selection.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.covideu.databinding.FragmentAllHealthNewsBinding
import com.example.covideu.model.covidNews.allHealthNews.AllHeathNewsModel
import com.example.covideu.view.ViewModels.newsViewModels.allHealthNewsViewModel
import com.example.covideu.view.adapter.newsRecyclers.showAllHealthNewsRecyclerView


class AllHealthFragment : Fragment() {
    private lateinit var binding: FragmentAllHealthNewsBinding
    private lateinit var showAllHealthNewsAdapter: showAllHealthNewsRecyclerView
    private var healthNewsList = listOf<AllHeathNewsModel>()
    private val allHealthViewModelViewModel: allHealthNewsViewModel by activityViewModels()
    var pageNumber = 0

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

        showAllHealthNewsAdapter = showAllHealthNewsRecyclerView(allHealthViewModelViewModel,requireContext())
        binding.allHealthNewsRecyclerView.adapter =showAllHealthNewsAdapter
        binding.progressBarAllHealth.animate().alpha(0f).setDuration(1000)

        allHealthViewModelViewModel.callAllHealthNews(pageNumber)

        observeCovidNews()
        binding.progressBarAllHealth.animate().alpha(1f)









    }
    @SuppressLint("NotifyDataSetChanged")
    fun observeCovidNews(){

        allHealthViewModelViewModel.covidAllHeathLiveData .observe(viewLifecycleOwner,{
        it?.let {
        }
            Log.d("covidNews",it.toString())
            showAllHealthNewsAdapter.submitList(it)
            healthNewsList =it


        })
    }

}