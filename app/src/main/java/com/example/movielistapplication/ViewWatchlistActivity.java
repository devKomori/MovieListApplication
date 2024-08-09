package com.example.movielistapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movielistapplication.BrowseMoviesActivity.OnListItemClick;
import com.example.movielistapplication.Database.MovieListRepository;
import com.example.movielistapplication.databinding.ActivityViewWatchlistBinding;
import com.example.movielistapplication.viewholders.MovieListAdapter;
import com.example.movielistapplication.viewholders.MovieListViewModel;

public class ViewWatchlistActivity extends AppCompatActivity {

  private ActivityViewWatchlistBinding binding;
  private MovieListRepository repository;
  private MovieListViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityViewWatchlistBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

    RecyclerView recyclerView = binding.viewWatchlistRecyclerView;
    final MovieListAdapter adapter = new MovieListAdapter();
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    repository = MovieListRepository.getRepository(getApplication());

    viewModel.getAllMovies().observe(this, movies -> {
      adapter.setMovies(movies);
    });

    BrowseMoviesActivity.OnListItemClick onListItemClick = new BrowseMoviesActivity.OnListItemClick() {
      @Override
      public void onClick(View view, int position) {
        Intent intent = MovieDetailsActivity.movieDetailsIntentFactory(getApplicationContext(),
            adapter.getMovieAtPosition(position));
        startActivity(intent);
      }
    };
    adapter.setClickListener(onListItemClick);
  }

  /* Factory method to create an intent for this activity */
  public static Intent viewWatchlistIntentFactory(Context context) {
    return new Intent(context, ViewWatchlistActivity.class);
  }
}