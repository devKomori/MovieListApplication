package com.example.movielistapplication.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielistapplication.R;

public class MovieListViewHolder extends RecyclerView.ViewHolder {

    // Display widget stuff
    TextView title;
    ConstraintLayout movieListCardView;
    ImageView movieImage;


    public MovieListViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.movieNameTextViewForMovieItem);
        movieListCardView = itemView.findViewById(R.id.movieItemConstraintLayout);
        movieImage = itemView.findViewById(R.id.movieImageViewForMoviePoster);

    }
}
