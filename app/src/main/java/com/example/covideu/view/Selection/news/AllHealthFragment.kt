package com.example.covideu.view.Selection.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covideu.databinding.FragmentAllHealthNewsBinding
import com.example.covideu.model.covidNews.allHealthNews.AllHeathNewsModel
import com.example.covideu.view.ViewModels.newsViewModels.allHealthNewsViewModel
import com.example.covideu.view.adapter.newsRecyclers.allVaccineNewsRecyclerView
import com.example.covideu.view.adapter.newsRecyclers.showAllHealthNewsRecyclerView
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toDuration

private const val TAG = "AllHealthFragment"
class AllHealthFragment : Fragment() {
    private lateinit var binding: FragmentAllHealthNewsBinding
    private lateinit var showAllHealthNewsAdapter: showAllHealthNewsRecyclerView
    private var healthNewsList = mutableListOf<AllHeathNewsModel>()
    private val allHealthViewModelViewModel: allHealthNewsViewModel by activityViewModels()
    var pageNumber = 0

    var loading = false
    var thePager =0
    private lateinit var  layoutManger: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        binding = FragmentAllHealthNewsBinding.inflate(inflater,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManger = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        binding.allHealthNewsRecyclerView.layoutManager = layoutManger
        showAllHealthNewsAdapter = showAllHealthNewsRecyclerView(allHealthViewModelViewModel, requireContext())
        binding.allHealthNewsRecyclerView.adapter = showAllHealthNewsAdapter
        observeCovidNews()
        allHealthViewModelViewModel.callAllHealthNews()
        page()







    }
    @SuppressLint("NotifyDataSetChanged")
    fun observeCovidNews(){

        allHealthViewModelViewModel.covidAllHeathLiveData .observe(viewLifecycleOwner,{
        it?.let {
            binding.allHealthNewsProgressBar.visibility = View.VISIBLE

            Log.d("covidNews",it.toString())
            healthNewsList.addAll(it)
            showAllHealthNewsAdapter.submitList(healthNewsList)
            loading = false
            binding.allHealthNewsProgressBar.visibility = View.GONE


        }
            Thread.sleep(3000)
            binding.progressBarAllHealth.visibility = View.GONE




        })
    }

    fun page(){
        Log.d("page","I am inside")
        // paging



        binding.allHealthNewsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!loading) {
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        loading = true
                        binding.progressBarAllHealth.visibility = View.VISIBLE

                        allHealthViewModelViewModel.callAllHealthNews()
                        Log.d(TAG, "end")
                    }
                }
            }
        })

    }

}