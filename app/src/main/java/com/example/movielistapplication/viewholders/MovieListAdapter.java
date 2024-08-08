package com.example.movielistapplication.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.movielistapplication.BrowseMoviesActivity.OnListItemClick;
import com.example.movielistapplication.Database.entities.Movie;
import com.example.movielistapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private OnListItemClick onListItemClick;

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieListViewHolder(view);
    }


    /**
     * Binds the data to the views in MovieListViewHolder.
     * The method is used by RecyclerView to display the data at the specified position.
     * The movie's poster image is loaded using Glide.
     */
    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.rating.setText(String.format(Locale.US, "%.1f", movie.getVoteAverage()));
        holder.releaseDate.setText(movie.getReleaseDate());
        holder.title.setText(movie.getTitle());
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + movie.getPosterPath())
                .into(holder.movieImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onListItemClick.onClick(view, holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }


    public Movie getMovieAtPosition(int position) {
        return movies.get(position);
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }


    public void addMovies(List<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }


    public void setClickListener(OnListItemClick context) {
        this.onListItemClick = context;
    }
}