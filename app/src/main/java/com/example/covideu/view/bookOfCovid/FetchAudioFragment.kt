package com.example.covideu.view.bookOfCovid

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.database.bookOfCovidDataClassAudio
import com.example.covideu.database.bookOfCovidDataClassVideos
import com.example.covideu.databinding.FragmentFetchAudioBinding
import com.example.covideu.databinding.FragmentFetchVideosBinding
import com.example.covideu.view.ViewModels.bookOfCovid.deleteBookOfCovidViewModel
import com.example.covideu.view.ViewModels.bookOfCovid.getBookOfCovidAudioViewModel
import com.example.covideu.view.ViewModels.bookOfCovid.getBookOfCovidPhotosViewModel
import com.example.covideu.view.adapter.bookOfCoivdVideosRecyclerview
import com.example.covideu.view.adapter.bookOfCovid.BookOfCovidAudioRecyclerView
import com.example.covideu.view.identity.SHARED_PREF_FILE


class FetchAudioFragment : Fragment() {
    private val fetchAudioViewModelViewModel: getBookOfCovidAudioViewModel by activityViewModels()
    private val DeleteAudioViewModelViewModel: deleteBookOfCovidViewModel by activityViewModels()
    private lateinit var audioRecyclerViewAdapter: BookOfCovidAudioRecyclerView

    lateinit var binding: FragmentFetchAudioBinding
    var theList = mutableListOf<bookOfCovidDataClassAudio>()
    private lateinit var  sharedPref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFetchAudioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)

        fetchAudioViewModelViewModel.getBookOfCovidAudio(sharedPref.getString("uid","")!!)
        audioRecyclerViewAdapter = BookOfCovidAudioRecyclerView(theList,requireContext(),DeleteAudioViewModelViewModel)
        binding.audioRecyclerView.adapter =audioRecyclerViewAdapter
        observeUri()
        checkForSuccessful()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeUri(){
        fetchAudioViewModelViewModel.uriLiveDataForAudio .observe(viewLifecycleOwner,{

            it?.let {
                theList.addAll(listOf(it))
                audioRecyclerViewAdapter.notifyDataSetChanged()
                Log.d("theVideo",theList.toString())
//                videoRecyclerViewAdapter.submitList(theList)


            }

        })

    }

    fun checkForSuccessful(){
        fetchAudioViewModelViewModel.userLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, "Successful uploaded", Toast.LENGTH_SHORT).show()

        })



    }
    fun checkForError(){

        fetchAudioViewModelViewModel.userLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, "Failed to upload image", Toast.LENGTH_SHORT).show()

        })
    }


}