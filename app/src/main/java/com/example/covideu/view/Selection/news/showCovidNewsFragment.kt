package com.example.covideu.view.Selection.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.covideu.databinding.FragmentShowCovidNewsBinding
import com.example.covideu.model.covidNews.allCovidNews.newsModel
import com.example.covideu.view.ViewModels.newsViewModels.CovidNewsViewModel
import com.example.covideu.view.adapter.newsRecyclers.showCovidnewsRecyclerView

private const val TAG = "showCovidNewsFragment"
class showCovidNewsFragment : Fragment() {
    private lateinit var binding: FragmentShowCovidNewsBinding
    private lateinit var showCovidNewsAdapter: showCovidnewsRecyclerView
    private val covidNewsList = mutableListOf<newsModel>()
    private val covidNewsViewModel: CovidNewsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowCovidNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showCovidNewsAdapter = showCovidnewsRecyclerView(covidNewsList)
        binding.covidNewsRecyclerView.adapter =showCovidNewsAdapter


        observeCovidNews()
        covidNewsViewModel.callAllCovidNews("1")






    }
    @SuppressLint("NotifyDataSetChanged")
    fun observeCovidNews(){

        covidNewsViewModel.covidAllNewsLiveData.observe(viewLifecycleOwner,{

            Log.d("covidNews",it.toString())
            covidNewsList.addAll(it)

            showCovidNewsAdapter.notifyDataSetChanged()
        })
    }




}