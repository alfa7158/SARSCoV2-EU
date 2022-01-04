package com.example.covideu.view.bookOfCovid

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.covideu.database.bookOfCovidDataClassVideos
import com.example.covideu.databinding.FragmentFetchVideosBinding
import com.example.covideu.view.ViewModels.bookOfCovid.bookOfCoivdViewModel
import com.example.covideu.view.adapter.bookOfCoivdVideosRecyclerview
import com.example.covideu.view.adapter.bookOfCovid.bookOfCovidImageViewRecyclerView


class FetchVideosFragment : Fragment() {
    private lateinit var videoRecyclerViewAdapter: bookOfCoivdVideosRecyclerview
    private val fetchVideoViewModel: bookOfCoivdViewModel by activityViewModels()
    // val imageList:ArrayList<bookOfCovidDataClass> = ArrayList()
    var theList = mutableListOf<bookOfCovidDataClassVideos>()
    lateinit var binding: FragmentFetchVideosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFetchVideosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fetchVideoViewModel.getBookOfCovidVideos()
        videoRecyclerViewAdapter = bookOfCoivdVideosRecyclerview(theList,requireContext())
        binding.videoRecyclerview.adapter =videoRecyclerViewAdapter
        observeUri()


    }

    fun checkForSuccessful(){
        fetchVideoViewModel.userLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, "Successful uploaded", Toast.LENGTH_SHORT).show()

        })



    }
    fun checkForError(){

        fetchVideoViewModel.userLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, "Failed to upload image", Toast.LENGTH_SHORT).show()

        })
    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeUri(){
        fetchVideoViewModel.uriLiveDataForVideos .observe(viewLifecycleOwner,{

            it?.let {
               theList.addAll(listOf(it))
                videoRecyclerViewAdapter.notifyDataSetChanged()
                Log.d("theVideo",theList.toString())
//                videoRecyclerViewAdapter.submitList(theList)


            }

        })

    }


}