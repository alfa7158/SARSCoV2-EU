package com.example.covideu.view.adapter.bookOfCovid

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.covideu.R
import com.example.covideu.database.bookOfCovidDataClassPhotos
import com.example.covideu.view.ViewModels.bookOfCovid.bookOfCoivdViewModel

class bookOfCovidImageViewRecyclerView(var fileContext:Context, val viewModel:bookOfCoivdViewModel) :
    RecyclerView.Adapter<bookOfCovidImageViewRecyclerView.bookOfCovidImageViewHolder>() {
    var xlist= mutableListOf<bookOfCovidDataClassPhotos>()

    // lateinit var uri:String
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): bookOfCovidImageViewRecyclerView.bookOfCovidImageViewHolder {

        return bookOfCovidImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_book_of_covid_layout,
                parent,
                false
            )
        )
    }

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<bookOfCovidDataClassPhotos>() {
        override fun areItemsTheSame(oldItem: bookOfCovidDataClassPhotos, newItem: bookOfCovidDataClassPhotos): Boolean {
            return oldItem.imageUri == newItem.imageUri
        }

        override fun areContentsTheSame(oldItem: bookOfCovidDataClassPhotos, newItem: bookOfCovidDataClassPhotos): Boolean {
            return  oldItem == newItem
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: bookOfCovidImageViewHolder, position: Int) {

        val item = differ.currentList[position]

        Glide.with(fileContext)
            .load(item.imageUri)
            .into(holder.imageBookOfCovid)
            holder.deleteButton.setOnClickListener {
                viewModel.deleteAnImage(item.imageUri)
                xlist.remove(item)
                notifyDataSetChanged()

            }




    }
    private val differ = AsyncListDiffer(this,DIFF_CALLBACK)



    override fun getItemCount(): Int {
        return differ.currentList.size

    }
    fun submitList(list: MutableList<bookOfCovidDataClassPhotos>) {
        xlist.clear()
       xlist.addAll(list)
        differ.submitList(xlist)
    }

    class bookOfCovidImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var imageBookOfCovid:ImageView = itemView.findViewById(R.id.imageViewBookOfCovid)
        var deleteButton:ImageView = itemView.findViewById(R.id.deleteImageView)
    }


}