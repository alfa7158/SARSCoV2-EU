package com.example.covideu.view.adapter
import android.net.Uri
import android.net.Uri.parse
import android.os.Environment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.core.net.toUri
import com.example.covideu.R
import com.example.covideu.database.bookOfCovidDataClassVideos

class bookOfCoivdVideosRecyclerview(private val list: MutableList<bookOfCovidDataClassVideos>, val fileContext:android.content.Context) :
  RecyclerView.Adapter<bookOfCoivdVideosRecyclerview.bookOfCoivdImageViewHolder>(){

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bookOfCoivdVideosRecyclerview.bookOfCoivdImageViewHolder {

      return bookOfCoivdImageViewHolder(
          LayoutInflater.from(parent.context).inflate(
              R.layout.item_book_of_videos_layout,
              parent,
              false
          )
      )
  }

  override fun onBindViewHolder(holder: bookOfCoivdImageViewHolder, position: Int) {


      val item = list[position]
      val mediaController = MediaController(fileContext)
      mediaController.setAnchorView(holder.videoView)
      holder.videoView.setMediaController(mediaController)
      holder.videoView.setVideoURI(item.videoUri.toUri())
      holder.videoView.requestFocus()
      holder.videoView.resume()

  }

  override fun getItemCount(): Int {
      return list.size
  }


  class bookOfCoivdImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
      val videoView = itemView.findViewById<VideoView>(R.id.videoView)
  }

}