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
import com.example.covideu.model.covidNews.allVaccineNews.allVaccineNews
import com.example.covideu.view.ViewModels.newsViewModels.allVaccineNewsViewModel
/**
 * This is the all Vaccine news adapter which its job to show list of vaccine news
 */
class allVaccineNewsRecyclerView(val newsViewModel: allVaccineNewsViewModel, var fileContext: Context) :
    RecyclerView.Adapter<allVaccineNewsRecyclerView.allVaccineNewsViewHolder>() {
    var theCount =0
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

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<allVaccineNews>() {
        override fun areItemsTheSame(oldItem: allVaccineNews, newItem: allVaccineNews): Boolean {
            return oldItem.newsId == newItem.newsId
        }

        override fun areContentsTheSame(oldItem: allVaccineNews, newItem: allVaccineNews): Boolean {
            return  oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this,DIFF_CALLBACK)


    override fun onBindViewHolder(holder: allVaccineNewsViewHolder, position: Int) {
        val item = differ.currentList[position]
        val x = position==(itemCount-1)
        Log.d("xVal",x.toString())

        Glide.with(fileContext)
            .load(item.urlToImage)
            .apply(RequestOptions().placeholder(R.drawable.fq))
            .into(holder.covidNewsImageView)
//        Glide.with(fileContext)
//            .load(item.urlToImage)
//            .into(holder.covidNewsImageView)
        holder.covidNewsTitle.text  = item.title
       // holder.pubDateTop.text = item.pubDate
        holder.pubDateBottom.text = item.pubDate
        //holder.content.text = item.content

        /**
         * the onClick listen is clicked to navigate from the recycler to the details fragment as
         * well as posts the value to covid19VaccineLiveDatDetails live data list
         */
        holder.covidNewsImageView.setOnClickListener {

            it.findNavController().navigate(R.id.action_showAllVaccineFragment_to_vaccineNewsFragmentDetails)
            newsViewModel.covid19VaccineLiveDatDetails.postValue(item)

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<allVaccineNews>) {
        differ.submitList(list)
    }

    class allVaccineNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val covidNewsImageView: ImageView = itemView.findViewById(R.id.vaccineNewsImageView)
        val covidNewsTitle: TextView = itemView.findViewById(R.id.vaccineNewsTitle)
    //    val pubDateTop: TextView = itemView.findViewById(R.id.dateTopAllVaccineNews)
        val pubDateBottom: TextView = itemView.findViewById(R.id.dateBottomAllVaccine)
     //   val content: TextView = itemView.findViewById(R.id.vaccineNewsContent)

    }

}