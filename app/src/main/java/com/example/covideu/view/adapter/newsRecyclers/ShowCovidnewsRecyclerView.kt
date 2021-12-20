package com.example.covideu.view.adapter.newsRecyclers

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.covideu.R
import com.example.covideu.model.covidNews.allCovidNews.newsModel
import com.squareup.picasso.Picasso

class showCovidnewsRecyclerView(private val list: MutableList<newsModel>) :
    RecyclerView.Adapter<showCovidnewsRecyclerView.showCovidNewsViewHolder>() {

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

    override fun onBindViewHolder(holder: showCovidNewsViewHolder, position: Int) {

        val item = list[position]
        Picasso.get().load(item.urlToImage).into(holder.covidNewsImageView)
        holder.covidNewsTitle.text  = item.title
//       holder.covidNewsContent.text = item.content
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class showCovidNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val covidNewsImageView:ImageView = itemView.findViewById(R.id.covidnewsImageView)
            val covidNewsTitle:TextView = itemView.findViewById(R.id.covidNewsTitle)

//            val covidNewsContent:TextView  = itemView.findViewById(R.id.covidNewscontent)

    }

}