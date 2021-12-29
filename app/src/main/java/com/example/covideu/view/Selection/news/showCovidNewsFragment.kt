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
import com.example.covideu.view.ViewModels.newsViewModels.covidNewsViewMode
import com.example.covideu.view.adapter.newsRecyclers.showCovidnewsRecyclerView

private const val TAG = "showCovidNewsFragment"
class showCovidNewsFragment : Fragment() {
    private lateinit var binding: FragmentShowCovidNewsBinding
    private lateinit var showCovidNewsAdapter: showCovidnewsRecyclerView
    private var covidNewsList = listOf<newsModel>()
    private val covidNewsViewModel: covidNewsViewMode by activityViewModels()

    var pageNumber = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowCovidNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showCovidNewsAdapter = showCovidnewsRecyclerView(covidNewsViewModel,requireContext())
        binding.covidNewsRecyclerView.adapter = showCovidNewsAdapter
        binding.covidNewsProgress.animate().alpha(0f).setDuration(1000)

        covidNewsViewModel.callAllCovidNews(pageNumber)
        observeCovidNews()

        binding.covidNewsRecyclerView.animate().alpha(1f)


        Log.d("hereIam",observeCovidNews().toString())

        //Log.d("checkIt", covidNewsViewModel.callAllCovidNews(showCovidNewsAdapter.thePage).toString())

        Log.d("checkMe", covidNewsList.lastIndex.toString())
//
//
//    }




    }
    @SuppressLint("NotifyDataSetChanged")
    fun observeCovidNews(){
        covidNewsViewModel.covidAllNewsLiveData.observe(viewLifecycleOwner,{
it?.let{

    Log.d("covidNews",it.toString())
    binding.covidNewsProgress.animate().alpha(0f).setDuration(1000)
    showCovidNewsAdapter.submitList(it)

    covidNewsList=it
    binding.covidNewsRecyclerView.animate().alpha(1f)
}
        })
    }




}
