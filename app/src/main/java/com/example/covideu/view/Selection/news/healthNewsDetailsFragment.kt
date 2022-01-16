package com.example.covideu.view.Selection.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.view.ViewModels.newsViewModels.allHealthNewsViewModel
/**
 * This class is responsible for showing details based on the item select on the recyclerview
 */

class healthNewsDetailsFragment : Fragment() {
private val covidNewsViewModel: allHealthNewsViewModel by activityViewModels()

    private lateinit var theWebHealth:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health_news_details, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView: WebView = view.findViewById(R.id.healthNewsWebView)
        val webButton: Button = view.findViewById(R.id.button_web_healthNews)
        /***
         * below the web view was used to show the news through implicit  intent
         */
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }








}