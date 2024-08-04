package com.example.movielistapplication.Database;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDBRequest {
  String API_KEY = "eaab2dae638911e60d7907ca349f1855";

  @GET("/3/movie/popular")
  Call<MovieApiJsonResponse> getPopularMovies(
          @Query("api_key") String key,
          @Query("page") String page
  );


  @GET("/3/search/movie")
  Call<MovieApiJsonResponse> searchMovies(
          @Query("api_key") String key,
          @Query("query") String query,
          @Query("page") String page
  );


}
