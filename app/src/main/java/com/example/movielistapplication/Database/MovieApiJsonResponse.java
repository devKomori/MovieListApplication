package com.example.movielistapplication.Database;

import com.example.movielistapplication.Database.entities.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieApiJsonResponse {

  @SerializedName("page")
  private int page;

  @SerializedName("total_pages")
  private int totalPages;

  @SerializedName("results")
  private List<Movie> results;

  @SerializedName("total_results")
  private int totalResults;


  public int getPage() {
    return page;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public List<Movie> getResults() {
    return results;
  }

  public int getTotalResults() {
    return totalResults;
  }
}