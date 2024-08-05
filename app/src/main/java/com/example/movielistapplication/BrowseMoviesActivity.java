package com.example.movielistapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielistapplication.Database.MovieApiData;
import com.example.movielistapplication.Database.MovieApiJsonResponse;
import com.example.movielistapplication.Database.MovieListRepository;

import com.example.movielistapplication.Database.TMDBRequest;
import com.example.movielistapplication.Database.entities.Movie;
import com.example.movielistapplication.databinding.ActivityBrowseMoviesBinding;
import com.example.movielistapplication.viewholders.MovieListAdapter;
import com.example.movielistapplication.viewholders.MovieListViewModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;


public class BrowseMoviesActivity extends AppCompatActivity {


  private ActivityBrowseMoviesBinding binding;
  private MovieListRepository repository;
  private MovieListAdapter adapter;
  private MovieListViewModel viewModel;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    binding = ActivityBrowseMoviesBinding.inflate(getLayoutInflater());
    super.onCreate(savedInstanceState);
    setContentView(binding.getRoot());

    repository = MovieListRepository.getRepository(getApplication());

    RecyclerView recyclerView = binding.popularMoviesRecyclerView;
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    adapter = new MovieListAdapter();
    recyclerView.setAdapter(adapter);

    viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
    //observeChange();

    Retrofit retrofit = new Builder()
        .baseUrl("https://api.themoviedb.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    TMDBRequest tmdbRequest = retrofit.create(TMDBRequest.class);
    List<Movie> movieList = getPopularMovies(tmdbRequest);
    adapter.setMovies(movieList);

  }

  private static @NonNull List<Movie> getPopularMovies(TMDBRequest tmdbRequest) {
    Call<MovieApiJsonResponse> call = tmdbRequest.getPopularMovies(TMDBRequest.API_KEY);
    List<Movie> movieList = new ArrayList<>();
    call.enqueue(new Callback<MovieApiJsonResponse>() {
      @Override
      public void onResponse(@NonNull Call<MovieApiJsonResponse> call,
          @NonNull Response<MovieApiJsonResponse> response) {
        MovieApiJsonResponse jsonResponse = response.body();
        List<MovieApiData> movieDataList;
        if (jsonResponse != null) {
          movieDataList = new ArrayList<>(Arrays.asList(jsonResponse.getMoviesArray()));
          for (MovieApiData movieApiData : movieDataList) {
            movieList.add(new Movie(movieApiData));
          }
        }
      }

      @Override
      public void onFailure(Call<MovieApiJsonResponse> call, Throwable throwable) {

      }
    });
    return movieList;
  }

  private void observeChange() {
        viewModel.getAllMovies().observe(this, movies -> {
            if (movies != null) {
                adapter.setMovies(movies);
            }
        });
    }
    

  public static Intent browseMoviesIntentFactory(Context context) {
    return new Intent(context, BrowseMoviesActivity.class);
  }


}