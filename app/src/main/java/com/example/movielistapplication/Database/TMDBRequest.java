package com.example.movielistapplication.Database;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDBRequest {
  String API_KEY = "e083a654cb4a233d677e4f54c3379249";

  @GET("/3/search/movie")
  Call<MovieApiJsonResponse> getMovies(@Query("query") String query,
    @Query("api_key") String api_key);
}
