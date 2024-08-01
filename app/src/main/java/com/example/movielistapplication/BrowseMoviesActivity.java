package com.example.movielistapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielistapplication.Database.MovieListRepository;

import com.example.movielistapplication.databinding.ActivityBrowseMoviesBinding;
import com.example.movielistapplication.viewholders.MovieListAdapter;
import com.example.movielistapplication.viewholders.MovieListViewModel;




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
        observeChange();





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