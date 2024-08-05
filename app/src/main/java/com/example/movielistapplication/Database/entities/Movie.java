package com.example.movielistapplication.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.movielistapplication.Database.MovieApiJsonResponse;
import com.example.movielistapplication.Database.MovieApiData;
import com.example.movielistapplication.Database.MovieListDatabase;

import com.example.movielistapplication.Database.TMDBRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    String processedTitle = title.replace(' ', '+');
    Call<MovieApiJsonResponse> call = tmdbRequest.getMovies(processedTitle, TMDBRequest.API_KEY);
    call.enqueue(new Callback<MovieApiJsonResponse>() {
      @Override
      public void onResponse(@NonNull Call<MovieApiJsonResponse> call,
          @NonNull Response<MovieApiJsonResponse> response) {
        MovieApiJsonResponse jsonResponse = response.body();
        List<MovieApiData> movieList;

        if (jsonResponse != null) {
          movieList = new ArrayList<>(Arrays.asList(jsonResponse.getMoviesArray()));
          movieId = movieList.get(0).getId();
          poster = movieList.get(0).getPoster_path();
          description = movieList.get(0).getOverview();
          rating = (int) (movieList.get(0).getVote_average() * 10);
          releaseDate = movieList.get(0).getRelease_date();
          /* TODO: Use TMDB API to actually convert these IDs to a string of genres.
           * https://api.themoviedb.org/3/genre/movie/list gives us an array of pairs of id & name
           */
          genres = Arrays.toString(movieList.get(0).getGenre_ids());
        }
      }

      @Override
      public void onFailure(@NonNull Call<MovieApiJsonResponse> call, @NonNull Throwable throwable) {
        // TODO: Display error message to user
      }
    });
  }

  public Movie(MovieApiData movieApiData) {
    this.movieId = movieApiData.getId();
    this.poster = movieApiData.getPoster_path();
    this.title = movieApiData.getTitle();
    this.description = movieApiData.getOverview();
    this.rating = (int) (movieApiData.getVote_average() * 10);
    this.releaseDate = movieApiData.getRelease_date();
    this.genres = Arrays.toString(movieApiData.getGenre_ids());
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
