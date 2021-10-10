package com.rick.groovy.playlist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rick.groovy.databinding.PlaylistItemBinding

class PlaylistRecyclerViewAdapter(
    private val values: List<Playlist>
) : RecyclerView.Adapter<PlaylistRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            PlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.setImageResource(item.image)
        holder.name.text = item.name
        holder.category.text = item.category
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: PlaylistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: ImageView = binding.image
        val name: TextView = binding.name
        val category: TextView = binding.category
        val item: TextView = binding.item
    }

}