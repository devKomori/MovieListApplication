package com.example.movielistapplication.Database;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TMDBRequest {
  String API_KEY = "e083a654cb4a233d677e4f54c3379249";

  @GET("/3/search/movie?query={title}&api_key=" + API_KEY)
  Call<MovieApiData> getMovie(@Path("title") String title);
}
