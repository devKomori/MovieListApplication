package com.example.movielistapplication.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movielistapplication.BrowseGenresActivity.OnListItemClick;
import com.example.movielistapplication.Genre;
import com.example.movielistapplication.R;
import java.util.ArrayList;
import java.util.List;

public class GenreListAdapter extends RecyclerView.Adapter<GenreListViewHolder> {

  private List<Genre> genres = new ArrayList<>();
  private OnListItemClick onListItemClick;

  @NonNull
  @Override
  public GenreListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.genre_item, parent, false);
    return new GenreListViewHolder(view);
  }

  /**
   * Binds the data to the views in MovieListViewHolder. The method is used by RecyclerView to
   * display the data at the specified position.
   */
  @Override
  public void onBindViewHolder(@NonNull GenreListViewHolder holder, int position) {
    Genre genre = genres.get(position);
    holder.genreButton.setText(genre.getName());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onListItemClick.onClick(view, holder.getAdapterPosition());
      }
    });
  }

  @Override
  public int getItemCount() {
    return genres.size();
  }


  public Genre getGenreAtPosition(int position) {
    return genres.get(position);
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
    notifyDataSetChanged();
  }


  public void addGenres(List<Genre> genres) {
    this.genres.addAll(genres);
    notifyDataSetChanged();
  }


  public void setClickListener(OnListItemClick context) {
    this.onListItemClick = context;
  }
}