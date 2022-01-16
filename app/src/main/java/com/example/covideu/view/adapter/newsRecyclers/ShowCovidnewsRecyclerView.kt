package com.example.covideu.view.adapter.newsRecyclers

import android.content.Context
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
import com.example.covideu.view.ViewModels.newsViewModels.covidNewsViewMode
/**
 * This is the all covid-19 news adapter which its job to show list of all covid-19 news
 */
private const val TAG = "ShowCovidnewsRecyclerVi"
class showCovidnewsRecyclerView(val list: MutableList<newsModel>, val newsViewModel:covidNewsViewMode, var fileContext: Context) :
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

        val item = list[position]


        Glide.with(fileContext)
            .load(item.urlToImage)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.fq)
            )
            .into(holder.vaccineNewsImageView)
//        Glide.with(fileContext)
//            .load(item.urlToImage)
//            .into(holder.covidNewsImageView)
            holder.covidNewsTitle.text  = item.title
//            holder.secCovidNewsProgressBar.animate().alpha(1F)
        /**
         * the onClick listen is clicked to navigate from the recycler to the details fragment as
         * well as posts the value to covidAllNewsLiveDataDetails live data list
         */
        holder.vaccineNewsImageView .setOnClickListener {

            it.findNavController().navigate(R.id.action_showCovidNewsFragment2_to_covidNewsDetailsFragment)
            newsViewModel.covidAllNewsLiveDataDetails.postValue(item)

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: MutableList<newsModel>) {
        differ.submitList(list)
    }




    class showCovidNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val vaccineNewsImageView:ImageView = itemView.findViewById(R.id.covidnewsImageView)
            val covidNewsTitle:TextView = itemView.findViewById(R.id.covidNewsTitle)
//            val secCovidNewsProgressBar:ProgressBar = itemView.findViewById(R.id.covidNewsProgress)
//            val covidNewsContent:TextView  = itemView.findViewById(R.id.covidNewscontent)

    }

}