package com.example.movielistapplication.Database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieApiJsonResponse {

  @SerializedName("results")
  @Expose
  private MovieApiData[] moviesArray;

  public MovieApiData[] getMoviesArray() {
    return moviesArray;
  }

  public void setMoviesArray(MovieApiData[] moviesArray) {
    this.moviesArray = moviesArray;
  }
}
