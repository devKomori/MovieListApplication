package com.example.movielistapplication.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielistapplication.R;

public class MovieListViewHolder extends RecyclerView.ViewHolder {

    // Display widget stuff
    TextView title;
    TextView rating;
    ImageView movieImage;
    TextView releaseDate;




    public MovieListViewHolder(@NonNull View itemView) {
        super(itemView);

        rating = itemView.findViewById(R.id.ratingTextView);
        title = itemView.findViewById(R.id.movieNameTextViewForMovieItem);
        movieImage = itemView.findViewById(R.id.movieImageViewForMoviePoster);
        releaseDate = itemView.findViewById(R.id.releaseDateTextView);


    }
}
