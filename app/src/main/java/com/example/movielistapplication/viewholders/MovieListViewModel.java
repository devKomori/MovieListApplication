package com.example.movielistapplication.viewholders;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movielistapplication.Database.MovieListRepository;
import com.example.movielistapplication.Database.entities.Movie;

import java.util.List;



public class MovieListViewModel extends AndroidViewModel {


    private final MovieListRepository repository;
    private final LiveData<List<Movie>> allMovies;


    public MovieListViewModel(Application application) {
        super(application);
        repository = MovieListRepository.getRepository(application);
        allMovies = repository.getAllMovies();
    }


    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }




}
