package com.example.movielistapplication.Database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.movielistapplication.Database.DAOS.MovieDao;
import com.example.movielistapplication.Database.DAOS.UserDao;
import com.example.movielistapplication.Database.entities.Movie;
import com.example.movielistapplication.Database.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@Database(entities = {User.class, Movie.class}, version = 2, exportSchema = false)
public abstract class MovieListDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "movieList_Database";
    public static final String USER_TABLE = "user_Table";
    public static final String MOVIE_TABLE = "movie_Table";
    public static final String TAG = "MovieListDatabase";

    private static volatile MovieListDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;


    /* Executor service to run database operations in the background. */
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * Returns singleton instance of the MovieListDatabase.
     * Creates the database if it does not exist
     */
    static MovieListDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieListDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    MovieListDatabase.class,
                                    DATABASE_NAME
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();

                }
            }
        }
        return INSTANCE;
    }


    /**
     * Callback to add default values to the database when it is created.
     * This will add an admin user and a test user to the database.
     */
    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i(MovieListDatabase.TAG, "Created the Database!");
            databaseWriteExecutor.execute(() -> {
                UserDao dao = INSTANCE.userDao();
                dao.deleteAll();
                User admin = new User("admin", "admin");
                admin.setAdmin(true);
                dao.insert(admin);

                User testUser = new User("testuser", "testuser");
                dao.insert(testUser);




            });
        }
    };


    public abstract UserDao userDao();
    public abstract MovieDao movieDao();



}
