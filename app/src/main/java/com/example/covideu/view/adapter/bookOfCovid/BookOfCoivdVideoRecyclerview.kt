package com.example.covideu.view.adapter
import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.covideu.R
import com.example.covideu.database.bookOfCovid.bookOfCovidDataClassVideos
import com.example.covideu.view.ViewModels.bookOfCovid.deleteBookOfCovidViewModel
import com.example.covideu.view.ViewModels.bookOfCovid.getBookOfCovidVideosViewModel
import com.google.firebase.auth.FirebaseAuth

class bookOfCoivdVideosRecyclerview(val fileContext:android.content.Context,val viewModelDelete: deleteBookOfCovidViewModel,val fetchVideoViewModel: getBookOfCovidVideosViewModel) :
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
            return oldItem.videoName == newItem.videoName
        }

        override fun areContentsTheSame(oldItem: bookOfCovidDataClassVideos, newItem: bookOfCovidDataClassVideos): Boolean {
            return  oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this,DIFF_CALLBACK)

  @SuppressLint("NotifyDataSetChanged")
  override fun onBindViewHolder(holder: bookOfCoivdImageViewHolder, position: Int) {


      val item = differ.currentList[position]
        holder.videoTitle.text = item.tit.toString()

      holder.thedescription.setText(item.description.toString())
//      val videoUri = "https://firebasestorage.googleapis.com/v0/b/covidutd-2ad5a.appspot.com/o/${item.videoName}?alt=media&token=6b7034c5-3a56-474a-a500-a5b5dfa94f9e"
//      val mediaController = MediaController(fileContext)
//      mediaController.setAnchorView(holder.videoView)
//      holder.videoView.setMediaController(mediaController)
//      holder.videoView.setVideoURI(videoUri.toUri())
//      holder.videoView.requestFocus()
//      holder.videoView.resume()
//      holder.videoView.setMediaController(mediaController)
//      holder.videoView.setFadingEdgeLength(20)

      holder.videoCardButton.setOnClickListener {
          it.findNavController().navigate(R.id.action_fetchVideosFragment_to_bookOfCoivdVideoFragmentDetails)

          fetchVideoViewModel.uriLiveDataForVideosDetails.postValue(item)

      }
      holder.deleteButton.setOnClickListener {


          if(item.uid== FirebaseAuth.getInstance().currentUser?.uid){
              item.videoName?.let { it1 -> viewModelDelete.deleteVideo(item) }
              xlist.remove(item)
              notifyDataSetChanged()

          }else{
              // holder.deleteButton.isVisible = false
              Toast.makeText(fileContext, "You are not allowed to delete", Toast.LENGTH_SHORT).show()
          }
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

      var deleteButton: ImageView = itemView.findViewById(R.id.deleteVideoView)
      var videoTitle: TextView = itemView.findViewById(R.id.titleBookOfCoivdVideo)
      var thedescription:EditText = itemView.findViewById(R.id.descriptionBookVideo)
      var videoCardButton:CardView = itemView.findViewById(R.id.cardVideoButton)
  }

}