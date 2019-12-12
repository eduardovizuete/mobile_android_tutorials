package com.example.myapplication

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_media_item.view.*
import kotlin.properties.Delegates

typealias Listener = (MediaItem) -> Unit

class MediaAdapter(data: List<MediaItem> = emptyList(), private val listener: Listener) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    var data by Delegates.observable(data) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.view_media_item), listener)

    // Replace the contents of a view (invoked by the layout manager)
    // Bind the content of the view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int = data.size

    // Provide a reference to the views for each data item
    // Each data item is an MediaItem.
    class ViewHolder(view: View, private val listener: Listener) : RecyclerView.ViewHolder(view) {

        fun bind(item: MediaItem) = with(itemView) {
            media_title.text = item.title
            media_thumb.loadUrl(item.thumbUrl)
            setOnClickListener { listener(item) }

            media_video_indicator.visibility = when (item.type) {
                MediaItem.Type.PHOTO -> View.GONE
                MediaItem.Type.VIDEO -> View.VISIBLE
            }
        }
    }

}