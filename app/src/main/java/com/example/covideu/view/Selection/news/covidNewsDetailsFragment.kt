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
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.view.ViewModels.newsViewModels.covidNewsViewMode
import com.squareup.picasso.Picasso


class covidNewsDetailsFragment : Fragment() {

    private val covidNewsViewModel: covidNewsViewMode by activityViewModels()
    private lateinit var theWeb:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_covid_news_details, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val webView: WebView = view.findViewById(R.id.covidNewsWebView)
        val webButton:Button = view.findViewById(R.id.button_web_view)

        covidNewsViewModel.covidAllNewsLiveDataDetails.observe(viewLifecycleOwner,{

            it?.let {
                theWeb = it.link
                webView.loadUrl(it.link)
                webButton.setOnClickListener {

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(theWeb)
                    startActivity(intent)
                }


            }

        })
    }


}