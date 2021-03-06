package com.example.covideu.view.adapter.newsRecyclers

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.covideu.R
import com.example.covideu.model.covidNews.allCovidNews.newsModel
import com.example.covideu.model.covidNews.allHealthNews.AllHeathNewsModel
import com.example.covideu.model.covidNews.allVaccineNews.allVaccineNews
import com.example.covideu.view.ViewModels.newsViewModels.allHealthNewsViewModel
/**
 * This is the all health news adapter which its job to show list of all health news
 */
class showAllHealthNewsRecyclerView(val newsViewModel:allHealthNewsViewModel,var fileContext: Context) :
    RecyclerView.Adapter<showAllHealthNewsRecyclerView.showAllHealthNewsVIewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): showAllHealthNewsVIewHolder {

        return showAllHealthNewsVIewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_all_health_news_layout,
                parent,
                false
            )
        )
    }


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AllHeathNewsModel>() {
        override fun areItemsTheSame(oldItem: AllHeathNewsModel, newItem: AllHeathNewsModel): Boolean {
            return oldItem.newsId == newItem.newsId
        }

        override fun areContentsTheSame(oldItem: AllHeathNewsModel, newItem: AllHeathNewsModel): Boolean {
            return  oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this,DIFF_CALLBACK)




    override fun onBindViewHolder(holder: showAllHealthNewsVIewHolder, position: Int) {

        val item = differ.currentList[position]


        Glide.with(fileContext)
            .load(item.urlToImage)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.fq)
            )
            .into(holder.allHealthNewsImageView)

//        Glide.with(fileContext)
//            .load(item.urlToImage)
//            .into(holder.allHealthNewsImageView)
        holder.covidNewsTitle.text  = item.title
        holder.pubDateBottom.text = item.pubDate

        /**
         * the onClick listen is clicked to navigate from the recycler to the details fragment as
         * well as posts the value to covidAllHeathLiveDataDetails live data list
         */
        holder.allHealthNewsImageView.setOnClickListener {

            it.findNavController().navigate(R.id.action_allHealthFragment_to_healthNewsDetailsFragment)
            newsViewModel.covidAllHeathLiveDataDetails.postValue(item)

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: MutableList<AllHeathNewsModel>) {
        differ.submitList(list)
    }


    class showAllHealthNewsVIewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val allHealthNewsImageView: ImageView = itemView.findViewById(R.id.allHealthNewsImageView)
        val covidNewsTitle: TextView = itemView.findViewById(R.id.allHealthNewsTitle)
        val pubDateBottom: TextView = itemView.findViewById(R.id.dateBottomAllHealth)

    }

}