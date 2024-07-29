package com.example.movielistapplication.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.movielistapplication.Database.MovieApiData;
import com.example.movielistapplication.Database.MovieListDatabase;

import com.example.movielistapplication.Database.TMDBRequest;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Entity(tableName = MovieListDatabase.MOVIE_TABLE)
public class Movie {

  @PrimaryKey()
  private int movieId;
  private String poster;
  private String title;
  private String description;
  private int rating;
  //private Duration runtime; // TMDB doesn't give us runtime. Will need to use a different API if we want this.
  private String releaseDate;
  private String genres;

  // Constructor
  public Movie(String title) {
    this.title = title;
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    TMDBRequest tmdbRequest = retrofit.create(TMDBRequest.class);
    tmdbRequest.getMovie(title.replace(' ', '+')).enqueue(new Callback<MovieApiData>() {
      @Override
      public void onResponse(@NonNull Call<MovieApiData> call,
          @NonNull Response<MovieApiData> response) {
        assert response.body() != null;
        poster = response.body().getResults().getPoster_path();
        description = response.body().getResults().getOverview();
        rating = (int) (Double.parseDouble(response.body().getResults().getVote_average()) * 10);
        releaseDate = response.body().getResults().getRelease_date();
        genres = response.body().getResults()
            .getGenre_ids(); // TODO: Use TMDB API to actually convert these IDs to a string of genres.
      }

      @Override
      public void onFailure(@NonNull Call<MovieApiData> call, @NonNull Throwable throwable) {

      }
    });
  }


  // Auto-generated getter and setter methods.
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getGenres() {
    return genres;
  }

  public void setGenres(String genres) {
    this.genres = genres;
  }

  public int getMovieId() {
    return movieId;
  }

  public void setMovieId(int movieId) {
    this.movieId = movieId;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  // Auto-generated equals and hashCode methods.
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Movie movie = (Movie) o;
    return movieId == movie.movieId && rating == movie.rating && Objects.equals(poster,
        movie.poster) && Objects.equals(title, movie.title) && Objects.equals(
        description, movie.description) && Objects.equals(releaseDate, movie.releaseDate)
        && Objects.equals(genres, movie.genres);
  }

  @Override
  public int hashCode() {
    return Objects.hash(movieId, poster, title, description, rating, releaseDate, genres);
  }
}