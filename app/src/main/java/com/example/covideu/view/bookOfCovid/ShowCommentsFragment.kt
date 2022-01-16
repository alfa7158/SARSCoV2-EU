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
import com.example.covideu.database.bookOfCovid.BookOfCovidDataClassComments
import com.example.covideu.databinding.FragmentShowCommentsBinding
import com.example.covideu.view.ViewModels.bookOfCovidViewModels.bookOfCovidCommentsViewModel
import com.example.covideu.view.ViewModels.bookOfCovidViewModels.deleteBookOfCovidViewModel
import com.example.covideu.view.ViewModels.bookOfCovidViewModels.getBookOfCovidCommentViewModel
import com.example.covideu.view.adapter.bookOfCovid.BookOfCovidCommentRecyclerView
import java.lang.Exception
/**
 * This class is to fetch comments from firebase
 */

class ShowCommentsFragment : Fragment() {
    private lateinit var commentRecyclerViewAdapter: BookOfCovidCommentRecyclerView

    private val addCommentsViewModelViewModel: bookOfCovidCommentsViewModel by activityViewModels()
    private val DeletePhotosViewModelViewModel: deleteBookOfCovidViewModel by activityViewModels()
    private val getCommentsViewModelViewModel: getBookOfCovidCommentViewModel by activityViewModels()
    lateinit var binding: FragmentShowCommentsBinding
    var commentsList = mutableListOf<BookOfCovidDataClassComments>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            getCommentsViewModelViewModel.getBookOfCovidComments()
            commentRecyclerViewAdapter = BookOfCovidCommentRecyclerView(commentsList)
            binding.commentsRecyclerView.adapter =commentRecyclerViewAdapter
            observeComments()

        }catch (e: Exception){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        binding.sendCommentImageView.setOnClickListener {
            val commentToBeAdded = binding.addCommentEditTex.text.toString()


            addCommentsViewModelViewModel.addCommentsFireStore(commentToBeAdded)

        }



    }

    @SuppressLint("NotifyDataSetChanged")

            /**
             * The function below is to observe comments from firebase
             */
    fun observeComments(){
        getCommentsViewModelViewModel.commentLiveData .observe(viewLifecycleOwner,{

            it?.let {
                binding.progressBarComment.visibility = View.VISIBLE

                //commentsList.clear()
                commentsList.addAll(it)
                commentRecyclerViewAdapter.notifyDataSetChanged()
                Log.d("theComments",commentsList.toString())
//                videoRecyclerViewAdapter.submitList(theList)
              //  getCommentsViewModelViewModel.commentLiveData.postValue(null)
                binding.progressBarComment.visibility = View.GONE



            }

        })

    }
}