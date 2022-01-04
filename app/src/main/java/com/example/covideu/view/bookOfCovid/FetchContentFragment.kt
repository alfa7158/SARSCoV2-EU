package com.example.covideu.view.bookOfCovid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.covideu.database.bookOfCovidDataClassPhotos
import com.example.covideu.databinding.FragmentFetchContentBinding
import com.example.covideu.view.ViewModels.bookOfCovid.bookOfCoivdViewModel
import com.example.covideu.view.adapter.bookOfCovid.bookOfCovidImageViewRecyclerView


class FetchContentFragment : Fragment() {
    private lateinit var imageRecyclerViewAdapter: bookOfCovidImageViewRecyclerView
    private val fetchPhotosViewModel: bookOfCoivdViewModel by activityViewModels()
   // val imageList:ArrayList<bookOfCovidDataClass> = ArrayList()
        var theList = mutableListOf<bookOfCovidDataClassPhotos>()
    lateinit var binding: FragmentFetchContentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFetchContentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchPhotosViewModel.getBookOfCovidPhotos()
        imageRecyclerViewAdapter = bookOfCovidImageViewRecyclerView(requireContext(),fetchPhotosViewModel)
        binding.bookOfCovidRecyclerView.adapter =imageRecyclerViewAdapter
        observeUri()


    }

    fun checkForSuccessful(){
        fetchPhotosViewModel.userLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, "Successful uploaded", Toast.LENGTH_SHORT).show()

        })



    }
    fun checkForError(){

        fetchPhotosViewModel.userLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, "Failed to upload image", Toast.LENGTH_SHORT).show()

        })
    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeUri(){
        fetchPhotosViewModel.uriLiveDataForPhotos .observe(viewLifecycleOwner,{

            it?.let {
                theList.addAll(listOf(it))
                imageRecyclerViewAdapter.submitList(theList)


            }

        })


    }
}