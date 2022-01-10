package com.example.covideu.view.Selection.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covideu.databinding.FragmentShowAllVaccineNewsBinding
import com.example.covideu.model.covidNews.allVaccineNews.allVaccineNews
import com.example.covideu.view.ViewModels.newsViewModels.allVaccineNewsViewModel
import com.example.covideu.view.adapter.newsRecyclers.allVaccineNewsRecyclerView
import com.example.covideu.view.adapter.newsRecyclers.showCovidnewsRecyclerView
import java.lang.Exception

private const val TAG = "showAllVaccineFragmentN"
class showAllVaccineFragment : Fragment() {
    private lateinit var binding: FragmentShowAllVaccineNewsBinding
    private lateinit var showVaccineNewsAdapter: allVaccineNewsRecyclerView
  private var vaccineNewsList = mutableListOf<allVaccineNews>()
    private val covidNewsViewModel: allVaccineNewsViewModel by activityViewModels()
    var pageNumber = 0
    var loading = false
    private lateinit var  layoutManger: LinearLayoutManager

    var thePager =0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        binding = FragmentShowAllVaccineNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManger = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        binding.allViccineNewsRecyclerView .layoutManager = layoutManger
        showVaccineNewsAdapter = allVaccineNewsRecyclerView(covidNewsViewModel, requireContext())
        binding.allViccineNewsRecyclerView.adapter = showVaccineNewsAdapter
        observeCovidNews()
        covidNewsViewModel.callAllVaccineNews()
        page()






    }
    @SuppressLint("NotifyDataSetChanged")
    fun observeCovidNews(){
        try {

            covidNewsViewModel.covid19VaccineLiveData.observe(viewLifecycleOwner,{
                it?.let {

                    binding.allVaccineProgressBar.visibility = View.VISIBLE

                    vaccineNewsList.addAll(it)
                    showVaccineNewsAdapter.submitList(vaccineNewsList)

                    loading = false
                    binding.allVaccineProgressBar.visibility = View.GONE


                }
                Thread.sleep(3000)

                binding.progressBarVaccinenNews.visibility = View.GONE
//            Log.d("covidNews",it.toString())

                // showVaccineNewsAdapter.notifyDataSetChanged()
            })
        }catch (e:Exception){

            checkForError()
        }

    }

    fun page(){
        Log.d("page","I am inside")


        binding.allViccineNewsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!loading) {
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        loading = true
                        binding.progressBarVaccinenNews.visibility = View.VISIBLE
                        covidNewsViewModel.callAllVaccineNews()
                        Log.d(TAG, "end")
                    }
                }
            }
        })

    }


    fun checkForSuccessful(){
        covidNewsViewModel.newsLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })

    }
    fun checkForError(){

        covidNewsViewModel.CovidLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })
    }

}