package com.example.covideu.view.adapter
import android.annotation.SuppressLint
import android.net.Uri
import android.net.Uri.parse
import android.os.Environment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import androidx.core.net.toUri
import com.example.covideu.R
import com.example.covideu.database.bookOfCovidDataClassVideos
import com.example.covideu.view.ViewModels.bookOfCovid.deleteBookOfCovidViewModel

class bookOfCoivdVideosRecyclerview(private val list: MutableList<bookOfCovidDataClassVideos>, val fileContext:android.content.Context,val viewModelDelete: deleteBookOfCovidViewModel) :
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

  @SuppressLint("NotifyDataSetChanged")
  override fun onBindViewHolder(holder: bookOfCoivdImageViewHolder, position: Int) {


      val item = list[position]
      val mediaController = MediaController(fileContext)
      mediaController.setAnchorView(holder.videoView)
      holder.videoView.setMediaController(mediaController)
      holder.videoView.setVideoURI(item.videoUri.toUri())
      holder.videoView.requestFocus()
      holder.videoView.resume()
      holder.videoView.setMediaController(mediaController)
      holder.videoView.setFadingEdgeLength(20)

      holder.deleteButton.setOnClickListener {

          viewModelDelete.deleteAnImage(item.videoUri)
          list.remove(item)
          notifyDataSetChanged()
      }


  }

  override fun getItemCount(): Int {
      return list.size
  }


  class bookOfCoivdImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
      val videoView = itemView.findViewById<VideoView>(R.id.videoView)
      var deleteButton: ImageView = itemView.findViewById(R.id.deleteVideoView)

  }

}