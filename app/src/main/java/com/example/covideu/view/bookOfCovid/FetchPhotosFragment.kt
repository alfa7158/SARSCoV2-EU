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
import com.example.covideu.database.bookOfCovid.bookOfCovidDataClassPhotos
import com.example.covideu.databinding.FragmentFetchContentBinding
import com.example.covideu.view.ViewModels.bookOfCovid.deleteBookOfCovidViewModel
import com.example.covideu.view.ViewModels.bookOfCovid.getBookOfCovidPhotosViewModel
import com.example.covideu.view.adapter.bookO.bookOfCovidImageViewRecyclerView
import com.example.covideu.view.identity.SHARED_PREF_FILE


class FetchPhotosFragment : Fragment() {
    private lateinit var  sharedPref: SharedPreferences
    private lateinit var imageRecyclerViewAdapter: bookOfCovidImageViewRecyclerView
    private val fetchPhotosViewModelViewModel: getBookOfCovidPhotosViewModel by activityViewModels()
    private val DeletePhotosViewModelViewModel: deleteBookOfCovidViewModel by activityViewModels()
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
//        fetchPhotosViewModelViewModel.`getBookOfCovidPhotos(sharedPref.getString("uid","")!!)
        fetchPhotosViewModelViewModel.getBookOfCovidPhotos()
        imageRecyclerViewAdapter = bookOfCovidImageViewRecyclerView(requireContext(),fetchPhotosViewModelViewModel,DeletePhotosViewModelViewModel)
        binding.bookOfCovidRecyclerView.adapter =imageRecyclerViewAdapter
        observeUri()


    }

    fun checkForSuccessful(){
        fetchPhotosViewModelViewModel.userLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })



    }
    fun checkForError(){

        fetchPhotosViewModelViewModel.userLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })
    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeUri(){
        fetchPhotosViewModelViewModel.uriLiveDataForPhotos.observe(viewLifecycleOwner,{

            it?.let {
                binding.progressBarPhoto.visibility = View.VISIBLE

                theList.clear()
                theList.addAll(it)
                imageRecyclerViewAdapter.submitList(theList)
                Log.d("thePhoto",theList.toString())
                binding.progressBarPhoto.visibility = View.GONE


                // fetchPhotosViewModelViewModel.uriLiveDataForPhotos.postValue(null)

            }
            checkForError()
        })


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }
}