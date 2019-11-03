package com.example.aaaaaaa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class MovieAdapter(val items : List<mapMovieDB>, val context: Context) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_item_layout,
            parent, false
        )
        return ViewHolder(view_item)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title?.text = items[position].title
        holder.year?.text = items[position].year.toString()
        Glide.with(context)
            .load(items[position].img_movie)
            .into(holder.imageMovie)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.title
        val year = view.year
        val imageMovie = view.imageMovie
    }
}
