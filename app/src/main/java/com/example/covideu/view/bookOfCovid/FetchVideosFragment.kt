package com.example.covideu.view.bookOfCovid

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.database.bookOfCovid.bookOfCovidDataClassVideos
import com.example.covideu.databinding.FragmentFetchVideosBinding
import com.example.covideu.view.ViewModels.bookOfCovid.deleteBookOfCovidViewModel
import com.example.covideu.view.ViewModels.bookOfCovid.getBookOfCovidVideosViewModel
import com.example.covideu.view.adapter.bookOfCoivdVideosRecyclerview
import com.example.covideu.view.identity.SHARED_PREF_FILE
import java.lang.Exception


class FetchVideosFragment : Fragment() {
    private lateinit var  sharedPref: SharedPreferences

    private lateinit var videoRecyclerViewAdapter: bookOfCoivdVideosRecyclerview
    private val fetchVideoViewModel: getBookOfCovidVideosViewModel by activityViewModels()
    private val DeletePhotosViewModelViewModel: deleteBookOfCovidViewModel by activityViewModels()

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    try {
        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
      fetchVideoViewModel.getBookOfCovidVideos()
        videoRecyclerViewAdapter = bookOfCoivdVideosRecyclerview(requireContext(),DeletePhotosViewModelViewModel,fetchVideoViewModel)
        binding.videoRecyclerview.adapter =videoRecyclerViewAdapter
        observeUri()
        checkForSuccessful()

    }catch (e:Exception){
        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        checkForError()
    }


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
                binding.progressBarVideo.visibility = View.VISIBLE

                theList.clear()
                theList.addAll(it)
                videoRecyclerViewAdapter.submitList(theList)
                Log.d("theVideo",theList.toString())
//                videoRecyclerViewAdapter.submitList(theList)
                fetchVideoViewModel.uriLiveDataForVideos.postValue(null)
                binding.progressBarVideo.visibility = View.GONE



            }

        })

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }


}