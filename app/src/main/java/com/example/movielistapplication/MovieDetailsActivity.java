package com.example.movielistapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.movielistapplication.Database.MovieListRepository;
import com.example.movielistapplication.Database.entities.Movie;
import com.example.movielistapplication.databinding.ActivityMovieDetailsBinding;

public class MovieDetailsActivity extends AppCompatActivity {
  private static final String TAG = "MovieDetailsActivity";
  private static final String MOVIE_DETAILS_TITLE = "com.example.movielistapplication.MOVIE_DETAILS_TITLE";
  private static final String MOVIE_DETAILS_OVERVIEW = "com.example.movielistapplication.MOVIE_DETAILS_OVERVIEW";
  private static final String MOVIE_DETAILS_POSTER = "com.example.movielistapplication.MOVIE_DETAILS_POSTER";
  private static final String MOVIE_DETAILS_RATING = "com.example.movielistapplication.MOVIE_DETAILS_RATING";
  private static final String MOVIE_DETAILS_RELEASE_DATE = "com.example.movielistapplication.MOVIE_DETAILS_RELEASE_DATE";

  private ActivityMovieDetailsBinding binding;
  private MovieListRepository repository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
    super.onCreate(savedInstanceState);
    setContentView(binding.getRoot());
    repository = MovieListRepository.getRepository(getApplication());

    binding.movieNameTextViewForMovieItem.setText(getIntent().getStringExtra(MOVIE_DETAILS_TITLE));
    binding.movieDetailsOverviewTextView.setText(getIntent().getStringExtra(MOVIE_DETAILS_OVERVIEW));
    Glide.with(binding.movieImageViewForMoviePoster.getContext())
        .load("https://image.tmdb.org/t/p/w500/" + getIntent().getStringExtra(MOVIE_DETAILS_POSTER))
        .into(binding.movieImageViewForMoviePoster);
    binding.ratingTextView.setText(getIntent().getStringExtra(MOVIE_DETAILS_RATING));
    binding.releaseDateTextView.setText(getIntent().getStringExtra(MOVIE_DETAILS_RELEASE_DATE));
    //TODO: "Add to Watchlist" button functionality
  }

  public static Intent movieDetailsIntentFactory(Context context, Movie movie) {
    Intent intent = new Intent(context, MovieDetailsActivity.class);
    intent.putExtra(MOVIE_DETAILS_TITLE, movie.getTitle());
    intent.putExtra(MOVIE_DETAILS_OVERVIEW, movie.getOverview());
    intent.putExtra(MOVIE_DETAILS_POSTER, movie.getPosterPath());
    intent.putExtra(MOVIE_DETAILS_RATING, String.valueOf(movie.getVoteAverage()));
    intent.putExtra(MOVIE_DETAILS_RELEASE_DATE, movie.getReleaseDate());
    return intent;
  }
}
