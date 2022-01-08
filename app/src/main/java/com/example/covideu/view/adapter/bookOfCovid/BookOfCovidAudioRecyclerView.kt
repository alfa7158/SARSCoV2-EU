package com.example.covideu.view.adapter.bookOfCovid

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
import com.example.covideu.R
import com.example.covideu.database.bookOfCovidDataClassAudio
import com.example.covideu.view.ViewModels.bookOfCovid.deleteBookOfCovidViewModel

class BookOfCovidAudioRecyclerView(private val list: List<bookOfCovidDataClassAudio>,val fileContext:Context,val DeleteAudioViewModelViewModel: deleteBookOfCovidViewModel) :
    RecyclerView.Adapter<BookOfCovidAudioRecyclerView.BookOfCovidAudioViewHolder>() {

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

    override fun onBindViewHolder(holder: BookOfCovidAudioViewHolder, position: Int) {

        val item = list[position]
        val mediaController = MediaController(fileContext)
        mediaController.setAnchorView(holder.audioView)
        holder.audioView.setMediaController(mediaController)
        holder.audioView.setVideoURI(item.audioUri.toUri())
        holder.audioView.requestFocus()
        holder.audioView.resume()
        holder.audioView.setMediaController(mediaController)
        holder.audioView.setFadingEdgeLength(30)
       // holder.audioView.setAudioFocusRequest(5)


    }

    override fun getItemCount(): Int {
        return list.size
    }


    class BookOfCovidAudioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val audioView = itemView.findViewById<VideoView>(R.id.audioView)
        var deleteButton: ImageView = itemView.findViewById(R.id.deleteAudioVideoView)

    }

}