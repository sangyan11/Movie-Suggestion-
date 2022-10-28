package com.sangyan.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sangyan.movies.databinding.MovieLayoutBinding

class TopRatedAdapter : RecyclerView.Adapter<TopRatedAdapter.ViewHolder>() {
    private var movieList = ArrayList<Result>()
    fun setTopRatedList(movieList : List<Result>){
        this.movieList = movieList as ArrayList<Result> /* = java.util.ArrayList<com.sangyan.movies.Result> */
        notifyDataSetChanged()
    }

    class ViewHolder(val binding : MovieLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            MovieLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500"+movieList[position].poster_path)
            .into(holder.binding.movieImage)
        if (movieList[position].title.length>=15) {
            holder.binding.movieName.text = (movieList[position].title).substring(0, 15) + "..."
        }
        else {
            holder.binding.movieName.text = movieList[position].title
        }
    }

    override fun getItemCount(): Int {
    return  movieList.size
    }
}