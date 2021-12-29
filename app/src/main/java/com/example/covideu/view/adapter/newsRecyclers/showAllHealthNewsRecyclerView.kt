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
import com.example.covideu.R
import com.example.covideu.model.covidNews.allCovidNews.newsModel
import com.example.covideu.model.covidNews.allHealthNews.AllHeathNewsModel
import com.example.covideu.view.ViewModels.newsViewModels.allHealthNewsViewModel

class showAllHealthNewsRecyclerView(val newsViewModel:allHealthNewsViewModel,var fileContext: Context) :
    RecyclerView.Adapter<showAllHealthNewsRecyclerView.showAllHealthNewsVIewHolder>() {
    var thePage =0

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
        val x = position==(itemCount-1)
        Log.d("xVal",x.toString())


        if(x){

            thePage++
            newsViewModel.callAllHealthNews(thePage)

            Log.d("thePage",thePage.toString())

            Log.d("please",newsViewModel.callAllHealthNews(thePage).toString())

        }
//            Picasso.get().load(item.urlToImage).into(holder.covidNewsImageView)
        Glide.with(fileContext)
            .load(item.urlToImage)
            .into(holder.allHealthNewsImageView)
        holder.covidNewsTitle.text  = item.title

        holder.allHealthNewsImageView.setOnClickListener {

            it.findNavController().navigate(R.id.action_allHealthFragment_to_healthNewsDetailsFragment)
            newsViewModel.covidAllHeathLiveDataDetails.postValue(item)

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<AllHeathNewsModel>) {
        differ.submitList(list)
    }


    class showAllHealthNewsVIewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val allHealthNewsImageView: ImageView = itemView.findViewById(R.id.allHealthNewsImageView)
        val covidNewsTitle: TextView = itemView.findViewById(R.id.AllHealthNewsTitle)

    }

}