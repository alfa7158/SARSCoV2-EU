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

class healthNewsDetailsFragment : Fragment() {
private val covidNewsViewModel: allHealthNewsViewModel by activityViewModels()

    private lateinit var theWebHealth:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView: WebView = view.findViewById(R.id.healthNewsWebView)
        val webButton: Button = view.findViewById(R.id.button_web_healthNews)

        covidNewsViewModel.covidAllHeathLiveDataDetails .observe(viewLifecycleOwner,{

            it?.let {
                theWebHealth = it.link
                webView.loadUrl(it.link)
                webButton.setOnClickListener {

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(theWebHealth)
                    startActivity(intent)
                }


            }

        })
    }



}