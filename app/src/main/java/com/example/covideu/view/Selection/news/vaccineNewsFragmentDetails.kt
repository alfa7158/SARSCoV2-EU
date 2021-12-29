package com.example.covideu.view.Selection.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.view.ViewModels.newsViewModels.allHealthNewsViewModel
import com.example.covideu.view.ViewModels.newsViewModels.allVaccineNewsViewModel


class vaccineNewsFragmentDetails : Fragment() {
    private lateinit var theWebVaccine:String
    private val allVaccineNewsViewModel: allVaccineNewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vaccine_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView: WebView = view.findViewById(R.id.vaccineNewsWebView)
        val webButton: Button = view.findViewById(R.id.button_web_vaccine)

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


    }
}