package com.example.covideu.view.adapter.newsRecyclers

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.covideu.R
import com.example.covideu.model.covidNews.allVaccineNews.allVaccineNews
import com.squareup.picasso.Picasso

class allVaccineNewsRecyclerView(private val list: List<allVaccineNews>) :
    RecyclerView.Adapter<allVaccineNewsRecyclerView.allVaccineNewsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): allVaccineNewsViewHolder {

        return allVaccineNewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_all_vaccine_news_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: allVaccineNewsViewHolder, position: Int) {

        val item = list[position]
        Picasso.get().load(item.urlToImage).into(holder.covidNewsImageView)
        holder.covidNewsTitle.text  = item.title
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class allVaccineNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val covidNewsImageView: ImageView = itemView.findViewById(R.id.vaccineNewsImageView)
        val covidNewsTitle: TextView = itemView.findViewById(R.id.vaccineNewsTitle)
    }

}