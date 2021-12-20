package com.example.covideu.view.adapter.newsRecyclers

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.covideu.R
import com.example.covideu.model.covidNews.allHealthNews.AllHeathNewsModel
import com.squareup.picasso.Picasso

class showAllHealthNewsRecyclerView(private val list: List<AllHeathNewsModel>) :
    RecyclerView.Adapter<showAllHealthNewsRecyclerView.showAllHealthNewsVIewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): showAllHealthNewsRecyclerView.showAllHealthNewsVIewHolder {

        return showAllHealthNewsVIewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_all_health_news_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: showAllHealthNewsVIewHolder, position: Int) {

        val item = list[position]
        Picasso.get().load(item.urlToImage).into(holder.covidNewsImageView)
        holder.covidNewsTitle.text  = item.title
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class showAllHealthNewsVIewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val covidNewsImageView: ImageView = itemView.findViewById(R.id.allHealthNewsImageView)
        val covidNewsTitle: TextView = itemView.findViewById(R.id.AllHealthNewsTitle)

    }

}