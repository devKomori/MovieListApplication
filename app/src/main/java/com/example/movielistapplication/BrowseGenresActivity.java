package com.example.movielistapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movielistapplication.Database.ApiRetrofitClient;
import com.example.movielistapplication.Database.MovieApiJsonResponse;
import com.example.movielistapplication.Database.TMDBRequest;
import com.example.movielistapplication.databinding.ActivityBrowseGenresBinding;
import com.example.movielistapplication.viewholders.GenreListAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowseGenresActivity extends AppCompatActivity {

  private static final String TAG = "BrowseGenresActivity";

  private ActivityBrowseGenresBinding binding;
  private GenreListAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    binding = ActivityBrowseGenresBinding.inflate(getLayoutInflater());
    super.onCreate(savedInstanceState);
    setContentView(binding.getRoot());

    GetRetrofitResponse();
    setupRecyclerView();
  }

  /**
   * Sends a request to the TMDB api to get movie genres and updates the adapter with the response.
   */
  private void GetRetrofitResponse() {
    TMDBRequest tmdbRequest = ApiRetrofitClient.getRetrofit().create(TMDBRequest.class);
    Call<MovieApiJsonResponse> responseCall = tmdbRequest.getGenres(TMDBRequest.API_KEY);
    responseCall.enqueue(new Callback<MovieApiJsonResponse>() {
      @Override
      public void onResponse(@NonNull Call<MovieApiJsonResponse> call,
          @NonNull Response<MovieApiJsonResponse> response) {
        if (response.code() == 200) {
          Log.v("Response Code", "the response code is: " + response.body().toString());

          List<Genre> genres = new ArrayList<>(response.body().getGenres());
          adapter.setGenres(genres);
        } else {
          try {
            Log.v("Response Code", "Error body" + response.errorBody().string());
          } catch (IOException e) {
            Log.e(TAG, "Error IOException", e);
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<MovieApiJsonResponse> call, @NonNull Throwable throwable) {
        Log.e(TAG, "API call failed", throwable);
      }
    });
  }

  /**
   * Sets up the recycler view for the activity and listens for scroll events. There is 20 movies
   * per page and when the user scrolls to the end of the list, the next page is fetched and methods
   * are called to add more movies and update the adapter.
   */
  private void setupRecyclerView() {
    RecyclerView recyclerView = binding.browseGenresRecyclerView;
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);

    adapter = new GenreListAdapter();
    recyclerView.setAdapter(adapter);
  }

  public static Intent browseGenresIntentFactory(Context context) {
    return new Intent(context, BrowseGenresActivity.class);
  }
}