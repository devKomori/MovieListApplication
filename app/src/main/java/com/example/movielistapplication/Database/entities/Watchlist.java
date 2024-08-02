package com.example.movielistapplication.Database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/*
public class Watchlist {
    import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

    @Entity(tableName = MovieListDatabase.WATCHLIST_TABLE,
            foreignKeys = {
                    @ForeignKey(entity = User.class,
                            parentColumns = "id",
                            childColumns = "userId",
                            onDelete = ForeignKey.CASCADE),
                    @ForeignKey(entity = Movie.class,
                            parentColumns = "id",
                            childColumns = "movieId",
                            onDelete = ForeignKey.CASCADE)
            })
    public class Watchlist {
        @PrimaryKey(autoGenerate = true)
        private int id;

        private int userId;
        private int movieId;
        private boolean watchedStatus;

        // Getters and setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public int getUserId() { return userId; }
        public void setUserId(int userId) { this.userId = userId; }

        public int getMovieId() { return movieId; }
        public void setMovieId(int movieId) { this.movieId = movieId; }

        public boolean isWatchedStatus() { return watchedStatus; }
        public void setWatchedStatus(boolean watchedStatus) { this.watchedStatus = watchedStatus; }
    }

}

*/
