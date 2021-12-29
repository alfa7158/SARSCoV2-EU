package com.example.covideu.view.adapter.newsRecyclers

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.covideu.R
import com.example.covideu.model.covidNews.allCovidNews.newsModel
import com.example.covideu.view.ViewModels.newsViewModels.covidNewsViewMode
import com.squareup.picasso.Picasso

class showCovidnewsRecyclerView( val newsViewModel:covidNewsViewMode,var fileContext: Context) :
    RecyclerView.Adapter<showCovidnewsRecyclerView.showCovidNewsViewHolder>() {
    var thePage=0
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): showCovidNewsViewHolder {

        return showCovidNewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item__covid_news_layout,
                parent,
                false
            )
        )
    }


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<newsModel>() {
        override fun areItemsTheSame(oldItem: newsModel, newItem: newsModel): Boolean {
            return oldItem.newsId == newItem.newsId
        }

        override fun areContentsTheSame(oldItem: newsModel, newItem: newsModel): Boolean {
            return  oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this,DIFF_CALLBACK)


    override fun onBindViewHolder(holder: showCovidNewsViewHolder, position: Int) {

        val item = differ.currentList[position]
        val x = position==(itemCount-1)
        Log.d("xVal",x.toString())


        if(x){

            thePage++
            newsViewModel.callAllCovidNews(thePage)

            Log.d("thePage",thePage.toString())

            Log.d("please",newsViewModel.callAllCovidNews(thePage).toString())

        }
//            Picasso.get().load(item.urlToImage).into(holder.covidNewsImageView)
        Glide.with(fileContext)
            .load(item.urlToImage)
            .into(holder.covidNewsImageView)
            holder.covidNewsTitle.text  = item.title
//            holder.secCovidNewsProgressBar.animate().alpha(1F)

        holder.covidNewsImageView .setOnClickListener {

            it.findNavController().navigate(R.id.action_showCovidNewsFragment2_to_covidNewsDetailsFragment)
            newsViewModel.covidAllNewsLiveDataDetails.postValue(item)

        }

//       holder.covidNewsContent.text = item.content
    }

    override fun getItemCount(): Int {
        return differ.currentList.size

    }

    fun submitList(list: List<newsModel>) {
        differ.submitList(list)
    }


    class showCovidNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val covidNewsImageView:ImageView = itemView.findViewById(R.id.covidnewsImageView)
            val covidNewsTitle:TextView = itemView.findViewById(R.id.covidNewsTitle)
//            val secCovidNewsProgressBar:ProgressBar = itemView.findViewById(R.id.covidNewsProgress)
//            val covidNewsContent:TextView  = itemView.findViewById(R.id.covidNewscontent)

    }

}