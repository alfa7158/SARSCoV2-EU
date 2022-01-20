package com.example.covideu.view.bookOfCovid

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.covideu.R
import com.example.covideu.databinding.FragmentBookOfCovidMainSelectBinding
import com.example.covideu.databinding.FragmentFetchContentBinding

/**
 * This class in the app home main selection fragment where all navigation happen from the main
 * select
 */

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainSelectBookOfCovidVideo.setBackgroundResource(R.drawable.shape_allu)
        binding.mainSelectBookOfCovidPic.setBackgroundResource(R.drawable.shape_allu)
        binding.mainSelectBookOfCovidAudio.setBackgroundResource(R.drawable.shape_allu)
        binding.bookOfCovidMainSelectPdf.setBackgroundResource(R.drawable.shape_allu)
        binding.mainSelectBookOfCovidDocx.setBackgroundResource(R.drawable.shape_allu)
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
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }


}