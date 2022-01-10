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
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.covideu.R
import com.example.covideu.database.bookOfCovidDataClassAudio
import com.example.covideu.database.bookOfCovidDataClassVideos
import com.example.covideu.view.ViewModels.bookOfCovid.deleteBookOfCovidViewModel

class bookOfCoivdVideosRecyclerview(val fileContext:android.content.Context,val viewModelDelete: deleteBookOfCovidViewModel) :
  RecyclerView.Adapter<bookOfCoivdVideosRecyclerview.bookOfCoivdImageViewHolder>(){
    var xlist= mutableListOf<bookOfCovidDataClassVideos>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bookOfCoivdVideosRecyclerview.bookOfCoivdImageViewHolder {

      return bookOfCoivdImageViewHolder(
          LayoutInflater.from(parent.context).inflate(
              R.layout.item_book_of_videos_layout,
              parent,
              false
          )
      )
  }

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<bookOfCovidDataClassVideos>() {
        override fun areItemsTheSame(oldItem: bookOfCovidDataClassVideos, newItem: bookOfCovidDataClassVideos): Boolean {
            return oldItem.videoUri == newItem.videoUri
        }

        override fun areContentsTheSame(oldItem: bookOfCovidDataClassVideos, newItem: bookOfCovidDataClassVideos): Boolean {
            return  oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this,DIFF_CALLBACK)

  @SuppressLint("NotifyDataSetChanged")
  override fun onBindViewHolder(holder: bookOfCoivdImageViewHolder, position: Int) {


      val item = differ.currentList[position]
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
          xlist.remove(item)
          notifyDataSetChanged()
      }


  }

  override fun getItemCount(): Int {
      return differ.currentList.size
  }
    fun submitList(list: MutableList<bookOfCovidDataClassVideos>) {
        xlist.clear()
        xlist.addAll(list)
        differ.submitList(xlist)
    }

  class bookOfCoivdImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
      val videoView = itemView.findViewById<VideoView>(R.id.videoView)
      var deleteButton: ImageView = itemView.findViewById(R.id.deleteVideoView)

  }

}