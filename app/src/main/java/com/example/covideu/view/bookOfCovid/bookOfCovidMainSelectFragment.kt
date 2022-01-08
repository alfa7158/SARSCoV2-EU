package com.example.covideu.view.bookOfCovid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.covideu.R
import com.example.covideu.databinding.FragmentBookOfCovidMainSelectBinding
import com.example.covideu.databinding.FragmentFetchContentBinding


class bookOfCovidMainSelectFragment : Fragment() {

    lateinit var binding:FragmentBookOfCovidMainSelectBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookOfCovidMainSelectBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainSelectBookOfCovidVideo.setOnClickListener {

        findNavController().navigate(R.id.action_bookOfCovidMainSelectFragment_to_fetchVideosFragment)
        }

        binding.mainSelectBookOfCovidPic.setOnClickListener {

            findNavController().navigate(R.id.action_bookOfCovidMainSelectFragment_to_fetchContentFragment)

        }


        binding.mainSelectBookOfCovidAudio.setOnClickListener {
            findNavController().navigate(R.id.action_bookOfCovidMainSelectFragment_to_fetchAudioFragment)

        }


        binding.bookOfCovidMainSelectPdf.setOnClickListener {

          //  findNavController().navigate()

        }

        binding.mainSelectBookOfCovidDocx.setOnClickListener {
          //  findNavController().navigate()


        }
        binding.fab.setOnClickListener{


            findNavController().navigate(R.id.action_bookOfCovidMainSelectFragment_to_uploadVideosFragment)
        }

    }
}