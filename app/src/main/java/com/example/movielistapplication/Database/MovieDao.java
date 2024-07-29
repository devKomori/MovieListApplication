package com.example.movielistapplication.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.movielistapplication.Database.entities.Movie;
import java.util.List;


@Dao
public interface MovieDao {

  // Insert data into the database and handle conflicts.
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(Movie... movie);


  @Delete
  void delete(Movie movie);


  @Query("DELETE from " + MovieListDatabase.MOVIE_TABLE)
  void deleteAll();


  // Get all Movies in the database
  @Query("SELECT * FROM " + MovieListDatabase.MOVIE_TABLE + " ORDER BY title")
  LiveData<List<Movie>> getAllMovies();


  // Get a Movie via its title
  @Query("SELECT * FROM " + MovieListDatabase.MOVIE_TABLE + " WHERE title == :title")
  LiveData<Movie> getMovieByTitle(String title);


  // Get a Movie via its movie id
  @Query("SELECT * FROM " + MovieListDatabase.MOVIE_TABLE + " WHERE movieId == :movieId")
  LiveData<Movie> getMovieByMovieId(int movieId);
}
