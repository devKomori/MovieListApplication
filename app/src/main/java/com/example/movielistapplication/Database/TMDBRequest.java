package com.example.movielistapplication.Database;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDBRequest {

  String API_KEY = "e083a654cb4a233d677e4f54c3379249";

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

  @GET("/3/discover/movie")
  Call<MovieApiJsonResponse> getMoviesByGenre(
      @Query("api_key") String key,
      @Query("with_genres") String genres,
      @Query("page") String page
  );

  @GET("/3/genre/movie/list")
  Call<MovieApiJsonResponse> getGenres(
      @Query("api_key") String key
  );
}
