package com.example.movielistapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.movielistapplication.Database.MovieListRepository;
import com.example.movielistapplication.Database.entities.Movie;
import com.example.movielistapplication.databinding.ActivityMovieDetailsBinding;
import com.google.gson.Gson;
import java.util.Locale;

public class MovieDetailsActivity extends AppCompatActivity {

  private static final String TAG = "MovieDetailsActivity";
  private static final String MOVIE_DETAILS_JSON = "com.example.movielistapplication.MOVIE_DETAILS_JSON";

  private ActivityMovieDetailsBinding binding;
  private MovieListRepository repository;
  private Movie displayedMovie;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
    super.onCreate(savedInstanceState);
    setContentView(binding.getRoot());
    repository = MovieListRepository.getRepository(getApplication());
    displayedMovie = new Gson().fromJson(getIntent().getStringExtra(MOVIE_DETAILS_JSON),
        Movie.class);

    binding.movieNameTextViewForMovieItem.setText(displayedMovie.getTitle());
    binding.movieDetailsOverviewTextView.setText(displayedMovie.getOverview());
    Glide.with(binding.movieImageViewForMoviePoster.getContext())
        .load("https://image.tmdb.org/t/p/w500/" + displayedMovie.getPosterPath())
        .into(binding.movieImageViewForMoviePoster);
    binding.ratingTextView.setText(String.format(Locale.US, "%.1f", displayedMovie.getVoteAverage()));
    binding.releaseDateTextView.setText(displayedMovie.getReleaseDate());
    //TODO: "Add to Watchlist" button functionality goes inside this listener's onClick()
    binding.addToWatchlistButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        repository.insertMovie(displayedMovie);
      }
    });
  }

  public static Intent movieDetailsIntentFactory(Context context, Movie movie) {
    Intent intent = new Intent(context, MovieDetailsActivity.class);
    intent.putExtra(MOVIE_DETAILS_JSON, new Gson().toJson(movie));
    return intent;
  }
}
