package com.example.movielistapplication;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.SearchView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielistapplication.Database.ApiRetrofitClient;
import com.example.movielistapplication.Database.MovieApiJsonResponse;
import com.example.movielistapplication.Database.MovieListRepository;

import com.example.movielistapplication.Database.TMDBRequest;
import com.example.movielistapplication.Database.entities.Movie;
import com.example.movielistapplication.databinding.ActivityBrowseMoviesBinding;
import com.example.movielistapplication.viewholders.MovieListAdapter;
import com.example.movielistapplication.viewholders.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BrowseMoviesActivity extends AppCompatActivity {

  private static final String TAG = "BrowseMoviesActivity";
  private ActivityBrowseMoviesBinding binding;
  private MovieListRepository repository;
  private MovieListAdapter adapter;
  private MovieListViewModel viewModel;
  private String movieQuery;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    binding = ActivityBrowseMoviesBinding.inflate(getLayoutInflater());
    super.onCreate(savedInstanceState);
    setContentView(binding.getRoot());

    repository = MovieListRepository.getRepository(getApplication());
    GetRetrofitResponse();
    MovieSearchView();
    setupRecyclerView();
    //setupViewModel(); // We don't want this since we want to browse TMDB's movies, not ours

    OnListItemClick onListItemClick = new OnListItemClick() {
      @Override
      public void onClick(View view, int position) {
        Intent intent = MovieDetailsActivity.movieDetailsIntentFactory(getApplicationContext(),
            adapter.getMovieAtPosition(position));
        startActivity(intent);
      }
    };
    adapter.setClickListener(onListItemClick);
  }


  /**
   * Sets up the ViewModel for the activity and observes changes and updates the adapter.
   */
  private void setupViewModel() {
    viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
    viewModel.getAllMovies().observe(this, movies -> {
      if (movies != null) {
        adapter.setMovies(movies);
        Log.d(TAG, "Movies observed: " + movies.size());
      } else {
        Log.d(TAG, "No movies found");
      }
    });
  }


  /**
   * Sends a request to the TMDB api to get popular movies and updates the adapter with the
   * response.
   */
  private void GetRetrofitResponse() {
    TMDBRequest tmdbRequest = ApiRetrofitClient.getRetrofit().create(TMDBRequest.class);
    Call<MovieApiJsonResponse> responseCall = tmdbRequest.getPopularMovies(
        TMDBRequest.API_KEY, "1");
    responseCall.enqueue(new Callback<MovieApiJsonResponse>() {
      @Override
      public void onResponse(@NonNull Call<MovieApiJsonResponse> call,
          @NonNull Response<MovieApiJsonResponse> response) {
        if (response.code() == 200) {
          Log.v("Response Code", "the response code is: " + response.body().toString());

          List<Movie> movies = new ArrayList<>(response.body().getResults());
          adapter.setMovies(movies);
        } else {
          try {
            Log.v("Response Code", "Error body" + response.errorBody().string());
          } catch (IOException e) {
            Log.e(TAG, "Error IOException", e);
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<MovieApiJsonResponse> call, @NonNull Throwable throwable) {
        Log.e(TAG, "API call failed", throwable);
      }
    });
  }


  /**
   * Sets up the search view for the activity and listens for query text changes.
   */
  private void MovieSearchView() {
    final SearchView searchView = findViewById(R.id.movieSearchView);
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        movieQuery = query;
        searchApiForMovies(query, 1); // Starts searching from the first page
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        return false;
      }
    });
  }


  /**
   * Searches the TMDB api for movies based on the search query and page number. The first page of
   * results is set to the adapter, while the next pages are added to the adapter. Any errors that
   * may occur are logged
   */
  private void searchApiForMovies(String query, int page) {
    TMDBRequest tmdbRequest = ApiRetrofitClient.getRetrofit().create(TMDBRequest.class);
    Call<MovieApiJsonResponse> responseCall = tmdbRequest.searchMovies(
        TMDBRequest.API_KEY, query, String.valueOf(page));

    responseCall.enqueue(new Callback<MovieApiJsonResponse>() {
      @Override
      public void onResponse(@NonNull Call<MovieApiJsonResponse> call,
          @NonNull Response<MovieApiJsonResponse> response) {
        if (response.code() == 200) {
          List<Movie> movies = response.body().getResults();
          if (page == 1) {
            adapter.setMovies(movies); // Sets the movies while searching for the first page
          } else {
            adapter.addMovies(movies); // Updates the adapter with the next page of movies
          }
          Log.d(TAG, "Search results: " + movies.size());
        } else {
          try {
            Log.e(TAG, "Error: " + response.errorBody().string());
          } catch (IOException e) {
            Log.e(TAG, "Error IOException", e);
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<MovieApiJsonResponse> call, @NonNull Throwable throwable) {
        Log.e(TAG, "API call failed", throwable);
      }
    });
  }


  /**
   * Sets up the recycler view for the activity and listens for scroll events. There is 20 movies
   * per page and when the user scrolls to the end of the list, the next page is fetched and methods
   * are called to add more movies and update the adapter.
   */
  private void setupRecyclerView() {
    RecyclerView recyclerView = binding.popularMoviesRecyclerView;
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);

    adapter = new MovieListAdapter();
    recyclerView.setAdapter(adapter);

    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (!recyclerView.canScrollVertically(1)) {
          int nextPage = adapter.getItemCount() / 20 + 1; // TMDB lists 20 movies per page
          searchApiForMovies(movieQuery, nextPage);
        }
      }
    });
  }


  /* Factory method to create an intent for this activity */
  public static Intent browseMoviesIntentFactory(Context context) {
    return new Intent(context, BrowseMoviesActivity.class);
  }

  public interface OnListItemClick {

    void onClick(View view, int position);
  }


}