package com.example.covideu.view.adapter.bookOfCovid

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.covideu.R
import com.example.covideu.database.bookOfCovidDataClassAudio
import com.example.covideu.database.bookOfCovidDataClassPhotos
import com.example.covideu.view.ViewModels.bookOfCovid.deleteBookOfCovidViewModel

class BookOfCovidAudioRecyclerView(val fileContext:Context,val DeleteAudioViewModel: deleteBookOfCovidViewModel) :
    RecyclerView.Adapter<BookOfCovidAudioRecyclerView.BookOfCovidAudioViewHolder>() {
    var xlist= mutableListOf<bookOfCovidDataClassAudio>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookOfCovidAudioRecyclerView.BookOfCovidAudioViewHolder {

        return BookOfCovidAudioViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_book_of_covid_audio_layout,
                parent,
                false
            )
        )
    }


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<bookOfCovidDataClassAudio>() {
        override fun areItemsTheSame(oldItem: bookOfCovidDataClassAudio, newItem: bookOfCovidDataClassAudio): Boolean {
            return oldItem.audioUri == newItem.audioUri
        }

        override fun areContentsTheSame(oldItem: bookOfCovidDataClassAudio, newItem: bookOfCovidDataClassAudio): Boolean {
            return  oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this,DIFF_CALLBACK)
    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: BookOfCovidAudioViewHolder, position: Int) {

        val item = differ.currentList[position]
        val mediaController = MediaController(fileContext)
        mediaController.setAnchorView(holder.audioView)
        holder.audioView.setMediaController(mediaController)
        holder.audioView.setVideoURI(item.audioUri.toUri())
        holder.audioView.requestFocus()
        holder.audioView.resume()
        holder.audioView.setMediaController(mediaController)
        holder.audioView.setFadingEdgeLength(30)
       // holder.audioView.setAudioFocusRequest(5)
        holder.deleteButton.setOnClickListener {

            DeleteAudioViewModel.deleteAnImage(item.audioUri)
            xlist.remove(item)
            notifyDataSetChanged()

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: MutableList<bookOfCovidDataClassAudio>) {
        xlist.clear()
        xlist.addAll(list)
        differ.submitList(xlist)
    }



    class BookOfCovidAudioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val audioView = itemView.findViewById<VideoView>(R.id.audioView)
        var deleteButton: ImageView = itemView.findViewById(R.id.deleteAudioVideoView)

    }

}