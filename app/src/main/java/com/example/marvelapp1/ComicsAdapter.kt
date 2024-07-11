package com.example.marvelapp1

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso




class ComicsAdapter(
    private val comics: List<Comic>,
    private val context: Context
) : RecyclerView.Adapter<ComicsAdapter.ComicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_comic, parent, false)
        return ComicViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = comics[position]
        holder.titleTextView.text = comic.title


        val textObject = comic.textObjects.firstOrNull { it.type == "issue_solicit_text" }
        val descriptionText = textObject?.text ?: "No description available"


        holder.descriptionTextView.text = if (descriptionText.length > 135) {
            "${descriptionText.substring(0, 135)}..."
        } else {
            descriptionText
        }


        val creatorsText = if (comic.creators.items.size > 3) {
            "Written by: " + comic.creators.items.take(3).joinToString(", ") { it.name } + "..."
        } else {
            "Written by: " + comic.creators.items.joinToString(", ") { it.name }
        }
        holder.creatorsTextView.text = creatorsText
        val imageUrl = "${comic.thumbnail.path}/portrait_xlarge.${comic.thumbnail.extension}"
        Picasso.get()
            .load("https${imageUrl.substring(4)}")
            .placeholder(R.drawable.baseline_downloading_24)
            .error(R.drawable.baseline_error_24)
            .into(holder.thumbnailImageView)

    }

    override fun getItemCount(): Int = comics.size

    class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val creatorsTextView: TextView = itemView.findViewById(R.id.creatorsTextView)
        val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)
    }
}

