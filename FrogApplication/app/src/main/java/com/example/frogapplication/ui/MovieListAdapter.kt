package com.coderus.codingchallenge.rocketlaunchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.frogapplication.R
import com.example.frogapplication.data.Search
import com.example.frogapplication.databinding.ListItemViewBinding


class MovieListAdapter(
    private val movies: List<Search>,
) : RecyclerView.Adapter<MovieListAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_view,
                parent,
                false
            )
        )


    override fun getItemCount() = movies.size

    inner class MoviesViewHolder(
        val recyclerviewMovieBinding: ListItemViewBinding
    ) : RecyclerView.ViewHolder(recyclerviewMovieBinding.root)

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
      holder.recyclerviewMovieBinding.search =  movies[position]

    }

}
