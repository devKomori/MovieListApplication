package com.example.movielistapplication.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "watchlist")
public class Watchlist {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private int movieId;
    private boolean watchedStatus;

    // Constructor
    public Watchlist(int userId, int movieId, boolean watchedStatus) {
        this.userId = userId;
        this.movieId = movieId;
        this.watchedStatus = watchedStatus;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public boolean isWatchedStatus() {
        return watchedStatus;
    }

    public void setWatchedStatus(boolean watchedStatus) {
        this.watchedStatus = watchedStatus;
    }
}
