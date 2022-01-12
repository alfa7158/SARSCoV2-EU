package com.example.covideu.view.adapter.bookO

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.covideu.R
import com.example.covideu.database.bookOfCovid.bookOfCovidDataClassPhotos
import com.example.covideu.view.ViewModels.bookOfCovid.deleteBookOfCovidViewModel
import com.example.covideu.view.ViewModels.bookOfCovid.getBookOfCovidPhotosViewModel
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "bookOfCovidImageViewRec"
class bookOfCovidImageViewRecyclerView(var fileContext:Context, val viewModel: getBookOfCovidPhotosViewModel, val viewModelDelete: deleteBookOfCovidViewModel) :
    RecyclerView.Adapter<bookOfCovidImageViewRecyclerView.bookOfCovidImageViewHolder>() {
    var xlist= mutableListOf<bookOfCovidDataClassPhotos>()
    var auth = FirebaseAuth.getInstance()

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
            return oldItem.imageName == newItem.imageName
        }

        override fun areContentsTheSame(oldItem: bookOfCovidDataClassPhotos, newItem: bookOfCovidDataClassPhotos): Boolean {
            return  oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this,DIFF_CALLBACK)


    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: bookOfCovidImageViewHolder, position: Int) {

        val item = differ.currentList[position]
        holder.theTitle.text = item.tit.toString()

        holder.theDescription.setText(item.description.toString())

        Glide.with(fileContext)
            .load("https://firebasestorage.googleapis.com/v0/b/covidutd-2ad5a.appspot.com/o/${item.imageName}?alt=media&token=6b7034c5-3a56-474a-a500-a5b5dfa94f9e")
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(holder.imageBookOfCovid)



            holder.deleteButton.setOnClickListener {
                if(item.uid==FirebaseAuth.getInstance().currentUser?.uid){
                    item.imageName?.let { it1 -> viewModelDelete.deleteAnImage(item) }
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
    fun submitList(list: MutableList<bookOfCovidDataClassPhotos>) {
        xlist.clear()
       xlist.addAll(list)
        differ.submitList(xlist)
    }

    class bookOfCovidImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var imageBookOfCovid:ImageView = itemView.findViewById(R.id.imageViewBookOfCovid)
        var deleteButton:ImageView = itemView.findViewById(R.id.deleteImageView)
        var theTitle:TextView = itemView.findViewById(R.id.titleImageBookOfCovid)
        var theDescription:EditText = itemView.findViewById(R.id.descriptionBookOfCovidImage)
    }


}