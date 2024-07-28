package com.example.movielistapplication.repository;
import android.app.Application;

import com.example.movielistapplication.daos.UserDao;
import java.util.concurrent.ExecutorService;

/**
 * Repository class to handle data operations for the Movie List Application.
 * TODO: Modify/import Movie and Watchlist.
 */
public class MLRepository {
    private UserDao userDao;
    private MovieDao movieDao;
    private WatchlistDao watchlistDao;
    private ExecutorService executorService;

    /**
     * Constructor to create an MLARepository.
     * TODO: Modify/import Movie and Watchlist.
     */
    public MLRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        userDao = database.userDao();
        movieDao = database.movieDao();
        watchlistDao = database.watchlistDao();
        executorService = Executors.newFixedThreadPool(4);
    }

    /**
     * User operations
     * Inserts a new user into the database.
     * TODO: Import User.
     */
    public void insertUser(User user) {
        executorService.execute(() -> userDao.insertUser(user));
    }

    /**
     * Retrieves a user by username and password.
     * TODO: Import User.
     */
    public User getUser(String username, String password) {
        return userDao.getUser(username, password);
    }

    /**
     * Retrieves all users from the database.
     * TODO: Import User.
     */
    public LiveData<List<User>> getAllUsers() {
        return userDao.getAllUsers();
    }
}

