package com.example.covideu.view.bookOfCovid.detailsFragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.MediaController
import android.widget.VideoView
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.view.ViewModels.bookOfCovidViewModels.getBookOfCovidVideosViewModel


class bookOfCoivdVideoFragmentDetails : Fragment() {
    private val fetchVideoViewModel: getBookOfCovidVideosViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_of_coivd_video_details, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val videoView = view.findViewById<VideoView>(R.id.videoView)

        fetchVideoViewModel.uriLiveDataForVideosDetails.observe(viewLifecycleOwner,{
            val videoUri = "https://firebasestorage.googleapis.com/v0/b/covidutd-2ad5a.appspot.com/o/${it.videoName}?alt=media&token=6b7034c5-3a56-474a-a500-a5b5dfa94f9e"
      val mediaController = MediaController(context)
      mediaController.setAnchorView(videoView)
    videoView.setMediaController(mediaController)
      videoView.setVideoURI(videoUri.toUri())
      videoView.requestFocus()
      videoView.start()
      videoView.setMediaController(mediaController)
      videoView.setFadingEdgeLength(20)





        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }










}