package com.example.movielistapplication.Database.DAOS;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.movielistapplication.Database.DAOS.UserDao;
import com.example.movielistapplication.Database.entities.Watchlist;

import java.util.List;

    @Dao
    public interface WatchlistDao {
        @Insert
        void insert(Watchlist watchlist);

        @Query("SELECT * FROM watchlist WHERE userId = :userId")
        List<Watchlist> getWatchlistForUser(int userId);

        @Query("DELETE FROM watchlist WHERE userId = :userId AND movieId = :movieId")
        void deleteFromWatchlist(int userId, int movieId);
    }

