package com.example.movielistapplication.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.movielistapplication.Database.MovieListDatabase;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = MovieListDatabase.MOVIE_TABLE)
public class Movie {

  @PrimaryKey()
  private int movieId;
  private String poster;
  private String title;
  private String description;
  private int rating;
  //private Duration runtime; // TMDB doesn't give us runtime. Will need to use a different API if we want this.
  private Date releaseDate;
  private String genre;

  // Constructor
  public Movie(String title) {
    this.title = title;
    // TODO: Retrofit API Request to fill remaining fields
    /*
    this.movieId = movieId;
    this.poster = poster;
    this.title = title;
    this.description = description;
    this.rating = rating;
    this.releaseDate = releaseDate;
    this.genre = genre;
    */
  }


  // Auto-generated getter and setter methods.
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
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

  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Date releaseDate) {
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
        && Objects.equals(genre, movie.genre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(movieId, poster, title, description, rating, releaseDate, genre);
  }
}

