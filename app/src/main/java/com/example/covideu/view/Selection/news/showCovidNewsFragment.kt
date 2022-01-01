package com.example.covideu.view.Selection.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covideu.databinding.FragmentShowCovidNewsBinding
import com.example.covideu.model.covidNews.allCovidNews.newsModel
import com.example.covideu.view.ViewModels.newsViewModels.covidNewsViewMode
import com.example.covideu.view.adapter.newsRecyclers.showCovidnewsRecyclerView

private const val TAG = "showCovidNewsFragment"
class showCovidNewsFragment : Fragment() {
    private lateinit var binding: FragmentShowCovidNewsBinding
    private lateinit var showCovidNewsAdapter: showCovidnewsRecyclerView

    private var covidNewsList = mutableListOf<newsModel>()
    private val covidNewsViewModel: covidNewsViewMode by activityViewModels()

    var pageNumber = 0
    var loading = false
    private lateinit var  layoutManger: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowCovidNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.covidNewsProgress.animate().alpha(0f).setDuration(1000)

//        covidNewsViewModel.callAllCovidNews(pageNumber)


       // binding.covidNewsRecyclerView.animate().alpha(1f)


        Log.d("hereIam", observeCovidNews().toString())

        //Log.d("checkIt", covidNewsViewModel.callAllCovidNews(showCovidNewsAdapter.thePage).toString())

        Log.d("checkMe", covidNewsList.lastIndex.toString())
//
//
//    }

        layoutManger = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.covidNewsRecyclerView.layoutManager = layoutManger
        showCovidNewsAdapter = showCovidnewsRecyclerView(covidNewsViewModel, requireContext())
        binding.covidNewsRecyclerView.adapter = showCovidNewsAdapter
        observeCovidNews()
        covidNewsViewModel.callAllCovidNews()
        page()



    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeCovidNews() {
        covidNewsViewModel.covidAllNewsLiveData.observe(viewLifecycleOwner, {
            it?.let {

                Log.d("covidNews", it.toString())
                binding.covidNewsProgress.animate().alpha(0f).duration = 1000
                covidNewsList.addAll(it)

                showCovidNewsAdapter.submitList(covidNewsList)
                loading = false

                binding.covidNewsRecyclerView.animate().alpha(1f)
            }
        })
    }


//    private fun checkPager() {
//
//
//
//    }

    fun page(){
        Log.d("page","I am inside")
        // paging
        var pastVisibleItems:Int
        var visibleItemCount: Int
        var totalItemCount: Int

        binding.covidNewsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = binding.covidNewsRecyclerView.getChildCount()
                    totalItemCount = binding.covidNewsRecyclerView.layoutManager!!.itemCount
                    pastVisibleItems = layoutManger.findFirstCompletelyVisibleItemPosition()
                    if (!loading) {
                        Log.d("loading1",loading.toString())

                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            Log.d("loading2",loading.toString())

                            Log.d("...", "Last Item Wow !")
                            binding.covidNewsProgress.visibility = View.VISIBLE
                            covidNewsViewModel.callAllCovidNews()
                            loading = true
                            Log.d("loading3",loading.toString())
                            // Do pagination.. i.e. fetch new data
                        }
                    }
                }
            }
        })
    }
}
