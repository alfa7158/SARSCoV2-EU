package com.example.covideu.view.bookOfCovid

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.MediaController
import android.widget.VideoView
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.view.ViewModels.bookOfCovid.getBookOfCovidAudioViewModel


class bookOfCoivdAudiFragmentDetails : Fragment() {
    private val fetchAudioViewModelViewModel: getBookOfCovidAudioViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_of_coivd_audi_details, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val audioView = view.findViewById<VideoView>(R.id.audioView)


        fetchAudioViewModelViewModel.uriLiveDataForAudioDetails.observe(viewLifecycleOwner,{
            val audioUri = "https://firebasestorage.googleapis.com/v0/b/covidutd-2ad5a.appspot.com/o/${it.audioName}?alt=media&token=6b7034c5-3a56-474a-a500-a5b5dfa94f9e"

            val mediaController = MediaController(context)
            mediaController.setAnchorView(audioView)
            audioView.setMediaController(mediaController)
            audioView.setVideoURI(audioUri.toUri())
            audioView.requestFocus()
            audioView.resume()
            audioView.setMediaController(mediaController)
            audioView.setFadingEdgeLength(20)





        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }


}