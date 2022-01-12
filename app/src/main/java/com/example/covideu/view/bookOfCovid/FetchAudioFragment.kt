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
import com.example.covideu.database.bookOfCovid.bookOfCovidDataClassAudio
import com.example.covideu.databinding.FragmentFetchAudioBinding
import com.example.covideu.view.ViewModels.bookOfCovid.deleteBookOfCovidViewModel
import com.example.covideu.view.ViewModels.bookOfCovid.getBookOfCovidAudioViewModel
import com.example.covideu.view.adapter.bookOfCovid.BookOfCovidAudioRecyclerView
import com.example.covideu.view.identity.SHARED_PREF_FILE
import java.lang.Exception


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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try{

            sharedPref = requireActivity().getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
            fetchAudioViewModelViewModel.getBookOfCovidAudio()
            audioRecyclerViewAdapter = BookOfCovidAudioRecyclerView(requireContext(),DeleteAudioViewModelViewModel,fetchAudioViewModelViewModel)
            binding.audioRecyclerView.adapter =audioRecyclerViewAdapter
            observeUri()
            checkForSuccessful()
        }catch (e:Exception){

            checkForError()


        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeUri(){
        fetchAudioViewModelViewModel.uriLiveDataForAudio .observe(viewLifecycleOwner,{

            it?.let {
                binding.progressBarAudio.visibility = View.VISIBLE

                theList.clear()
                theList.addAll(it)
                audioRecyclerViewAdapter.submitList(theList)
                Log.d("theVideo",theList.toString())
                binding.progressBarAudio.visibility = View.GONE

//                videoRecyclerViewAdapter.submitList(theList)
               // fetchAudioViewModelViewModel.uriLiveDataForAudio.postValue(null)

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
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }


}