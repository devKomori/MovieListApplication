package com.example.movielistapplication.Database;



import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;


import com.example.movielistapplication.Database.DAOS.MovieDao;
import com.example.movielistapplication.Database.DAOS.UserDao;
import com.example.movielistapplication.Database.entities.Movie;
import com.example.movielistapplication.Database.entities.User;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;



/**
 * Repository class to handle data operations for the Movie List Application.
 * TODO: Modify/import Watchlist.
 */
public class MovieListRepository {
    public static final String TAG = "MovieListRepository";
    private static MovieListRepository repository;
    private UserDao userDao;
    private MovieDao movieDao;
    //    TODO: Person implementing DAOs below are responsible for adding and implementing their methods.
//    private WatchlistDao watchlistDao;



    /**
     * Constructor to create an MovieListRepository.
     * TODO: Modify/import Watchlist.
     */
    public MovieListRepository(Application application) {
        MovieListDatabase db = MovieListDatabase.getDatabase(application);
        this.userDao = db.userDao();
        this.movieDao = db.movieDao();
    }


    /**
     * Retrieves a singleton instance of the MovieListRepository.
     * If the repository doesn't exist, it is created asynchronously and returned.
     * If an error occurs during creation, null is returned.
     */
    public static MovieListRepository getRepository(Application application) {
        if (repository != null) {
            return repository;
        }
        Future<MovieListRepository> future = MovieListDatabase.databaseWriteExecutor.submit(
                new Callable<MovieListRepository>() {
                    @Override
                    public MovieListRepository call() throws Exception {
                        return new MovieListRepository(application);
                    }
                });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.i(MovieListRepository.TAG, "Problem while getting repository, thread error.");
        }
        return null;
    }



    // User operations

    /* Deletes specified user from the database asynchronously. */
    public void delete(User user) {
        MovieListDatabase.databaseWriteExecutor.execute(() -> {
            userDao.delete(user);
        });
    }


    /* Inserts specified user into the database asynchronously. */
    public void insertUser(User user) {
        MovieListDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }


    /* Retrieves all users from the database. */
    public LiveData<List<User>> getAllUsers() {
        return userDao.getAllUsers();
    }


    /* Retrieves a user by their username from the database. */
    public LiveData<User> getUserByUserName(String username) {
        return userDao.getUserByUserName(username);
    }


    /* Retrieves a user by their user id from the database. */
    public LiveData<User> getUserByUserId(int userId) {
        return userDao.getUserByUserId(userId);
    }

    // Movie operations

    /**
     * Inserts a new movie into the database.
     */
    public void insertMovie(Movie... movie) {
        MovieListDatabase.databaseWriteExecutor.execute(() -> movieDao.insert(movie));
    }

    /**
     *  Deletes specified Movie from the database asynchronously.
     */
    public void delete(Movie movie) {
        MovieListDatabase.databaseWriteExecutor.execute(() -> {
            movieDao.delete(movie);
        });
    }

    /**
     *  Retrieves all Movies from the database.
     */
    public LiveData<List<Movie>> getAllMovies() {
        return movieDao.getAllMovies();
    }

    /**
     * Retrieves a Movie by its title from the database.
     */
    public LiveData<Movie> getMovieByTitle(String title) {
        return movieDao.getMovieByTitle(title);
    }

    /** Retrieves a Movie by its movie id (NOTE: This is set by TMDB, NOT auto-generated)
     *  from the database.
     */
    public LiveData<Movie> getMovieByMovieId(int movieId) {
        return movieDao.getMovieByMovieId(movieId);
    }




    // WatchList operations

//    /**
//     * WatchList operations
//     * Inserts a new watchlist entry into the database.
//     * TODO: Add the watchList operations methods. Requires WatchlistEntity & WatchlistDao.
//     */
//    public void insertWatchlist(Watchlist watchlist) {
//        MovieListDatabase.databaseWriteExecutor.execute(() -> watchlistDao.insert(watchlist));
//    }
}

