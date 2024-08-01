package com.example.movielistapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movielistapplication.Database.MovieListRepository;
import com.example.movielistapplication.databinding.ActivityBrowseMoviesBinding;


public class BrowseMoviesActivity extends AppCompatActivity {

    private ActivityBrowseMoviesBinding binding;
    private MovieListRepository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityBrowseMoviesBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());



    }




    public static Intent browseMoviesIntentFactory(Context context) {
        return new Intent(context, BrowseMoviesActivity.class);
    }



}