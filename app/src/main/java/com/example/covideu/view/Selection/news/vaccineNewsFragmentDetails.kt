package com.example.covideu.view.Selection.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.view.ViewModels.newsViewModels.allHealthNewsViewModel
import com.example.covideu.view.ViewModels.newsViewModels.allVaccineNewsViewModel
import java.lang.Exception


class vaccineNewsFragmentDetails : Fragment() {
    private lateinit var theWebVaccine:String
    private val allVaccineNewsViewModel: allVaccineNewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vaccine_news, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView: WebView = view.findViewById(R.id.vaccineNewsWebView)
        val webButton: Button = view.findViewById(R.id.button_web_vaccine)
        try {
            allVaccineNewsViewModel.covid19VaccineLiveDatDetails.observe(viewLifecycleOwner,{

                it?.let {
                    theWebVaccine = it.link
                    webView.loadUrl(it.link)
                    webButton.setOnClickListener {

                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(theWebVaccine)
                        startActivity(intent)
                    }


                }

            })

        }catch (e:Exception){

            checkForError()
        }



    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }


    fun checkForSuccessful(){
        allVaccineNewsViewModel.newsLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })

    }
    fun checkForError(){

        allVaccineNewsViewModel.CovidLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })
    }

}