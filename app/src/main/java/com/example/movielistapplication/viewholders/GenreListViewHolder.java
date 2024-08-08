package com.example.movielistapplication.viewholders;

import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movielistapplication.R;

public class GenreListViewHolder extends RecyclerView.ViewHolder {

    // Display widget stuff
    Button genreButton;




    public GenreListViewHolder(@NonNull View itemView) {
        super(itemView);

        genreButton = itemView.findViewById(R.id.genreItemButton);
    }
}
