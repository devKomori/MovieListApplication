package com.example.movielistapplication;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SearchView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
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
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityBrowseMoviesBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        GetRetrofitResponse();

        Toolbar toolbar = findViewById(R.id.searchToolbar);
        setSupportActionBar(toolbar);

        MovieSearchView();


        repository = MovieListRepository.getRepository(getApplication());

        RecyclerView recyclerView = binding.popularMoviesRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MovieListAdapter();
        recyclerView.setAdapter(adapter);

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





    private void GetRetrofitResponse() {
        TMDBRequest tmdbRequest = ApiRetrofitClient.getRetrofit().create(TMDBRequest.class);

        Call<MovieApiJsonResponse> responseCall = tmdbRequest.getPopularMovies(TMDBRequest.API_KEY, "1");
        responseCall.enqueue(new Callback<MovieApiJsonResponse>() {
            @Override
            public void onResponse(Call<MovieApiJsonResponse> call, Response<MovieApiJsonResponse> response) {
                if (response.code() == 200) {
                    Log.v("Response Code", "the response code is: " + response.body().toString());

                    List<Movie> movies = new ArrayList<>(response.body().getResults());
                    adapter.setMovies(movies);
                } else {
                    try {
                        Log.v("Response Code", "Error" + response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieApiJsonResponse> call, Throwable throwable) {
                Log.e(TAG, "API call failed", throwable);
            }
        });
    }


    public static Intent browseMoviesIntentFactory(Context context) {
        return new Intent(context, BrowseMoviesActivity.class);
    }




    private void MovieSearchView() {
        final SearchView searchView = findViewById(R.id.movieSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchApiForMovies(query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    private void searchApiForMovies(String query) {
        TMDBRequest tmdbRequest = ApiRetrofitClient.getRetrofit().create(TMDBRequest.class);
        Call<MovieApiJsonResponse> responseCall = tmdbRequest.searchMovies(TMDBRequest.API_KEY, query, "1" );

        responseCall.enqueue(new Callback<MovieApiJsonResponse>() {
            @Override
            public void onResponse(Call<MovieApiJsonResponse> call, Response<MovieApiJsonResponse> response) {
                if (response.code() == 200) {
                    List<Movie> movies = response.body().getResults();
                    adapter.setMovies(movies);

                    Log.d(TAG, "Search results: " + movies.size());
                } else {
                    try {
                        Log.e(TAG, "Error: " + response.errorBody().string());
                    } catch (IOException e) {
                        Log.e(TAG, "Error parsing error body", e);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieApiJsonResponse> call, Throwable t) {
                Log.e(TAG, "API call failed", t);
            }
        });
    }






}