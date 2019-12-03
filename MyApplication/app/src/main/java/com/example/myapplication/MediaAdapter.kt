package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MediaAdapter(val items: List<MediaItem>) : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val v = LayoutInflater.from(parent.context).inflate(R.layout.view_media_item, parent, false)
        val v = parent.inflate(R.layout.view_media_item)
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    // Bind the content of the view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int = items.size

    // Provide a reference to the views for each data item
    // Each data item is an MediaItem.
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title = find<TextView>(R.id.media_title)
        val image = find<ImageView>(R.id.image)
        val videoIndicator = find<ImageView>(R.id.media_video_indicator)

        fun bind(item: MediaItem) {
            title.text = item.title
            image.loadUrl(item.thumbUrl)
            videoIndicator.visibility = when (item.type) {
                MediaItem.Type.VIDEO -> View.VISIBLE
                MediaItem.Type.PHOTO -> View.GONE
            }
        }
    }

}