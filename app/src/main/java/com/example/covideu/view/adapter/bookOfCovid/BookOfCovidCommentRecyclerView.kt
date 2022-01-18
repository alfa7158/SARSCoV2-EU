package com.example.covideu.view.adapter.bookOfCovid

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.covideu.R
import com.example.covideu.database.bookOfCovid.BookOfCovidDataClassComments

/**
 * This is the comment adapter class which its job is to show the list of comments
 */
class BookOfCovidCommentRecyclerView(private val list: List<BookOfCovidDataClassComments>) :
    RecyclerView.Adapter<BookOfCovidCommentRecyclerView.BookOfCovidCommentViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookOfCovidCommentRecyclerView.BookOfCovidCommentViewHolder {

        return BookOfCovidCommentViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_comments_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookOfCovidCommentViewHolder, position: Int) {

        val item = list[position]

        holder.commentTextView.text = item.ImageId
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class BookOfCovidCommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commentTextView:TextView = itemView.findViewById(R.id.commentTextView)
    }

}