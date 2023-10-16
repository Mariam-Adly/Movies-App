package com.example.moviesapplication.moviesscreen.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapplication.R
import com.example.moviesapplication.model.Movie


class MoviesAdapter (private val onClick:(Movie)->Unit): ListAdapter<Movie, MoviesAdapter.ViewHolder>(MyDifUnit()) {
    lateinit var contxt: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        contxt=parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(contxt).load("https://www.themoviedb.org/t/p/w220_and_h330_face${getItem(position).poster_path}").placeholder(R.drawable.movie)
            .into(holder.img_movies)
        holder.tv_name.text = getItem(position).title
        holder.tv_date.text = getItem(position).release_date
        holder.constraint.setOnClickListener {
            onClick(getItem(position))
        }
    }


    inner class ViewHolder (private val itemView: View): RecyclerView.ViewHolder(itemView){
        val tv_name : TextView
            get() = itemView.findViewById(R.id.tv_name)
        val tv_date : TextView
            get() = itemView.findViewById(R.id.tv_date)
        val img_movies : ImageView
            get() = itemView.findViewById(R.id.img_movies)
        val constraint : ConstraintLayout
            get() = itemView.findViewById(R.id.constraint)
    }
}


class MyDifUnit: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}