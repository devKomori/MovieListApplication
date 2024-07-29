package com.example.movielistapplication.Database;
import android.app.Application;

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

    /**
     * Movie operations
     * Inserts a new movie into the database.
     * TODO: Add the Movie operations methods. Requires MovieEntity & MovieDao.
     */
    public void insertMovie(Movie movie) {
        executorService.execute(() -> movieDao.insertMovie(movie));
    }

    /**
     * Movie operations
     * Inserts a new watchlist entry into the database.
     * TODO: Add the watchList operations methods. Requires WatchlistEntity & WatchlistDao.
     */
    public void insertWatchlist(Watchlist watchlist) {
        executorService.execute(() -> watchlistDao.insertWatchlist(watchlist));
    }
}

