package com.example.movielistapplication.repository;

import com.example.movielistapplication.daos.UserDao;

import java.util.concurrent.ExecutorService;

/**
 * Repository class to handle data operations for the Movie List Application.
 */
public class MLARepository {
    private UserDao userDao;
    private MovieDao movieDao;
    private WatchlistDao watchlistDao;
    private ExecutorService executorService;
}
