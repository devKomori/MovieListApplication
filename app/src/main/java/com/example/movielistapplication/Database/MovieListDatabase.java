package com.example.movielistapplication.Database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.movielistapplication.Database.entities.User;
import com.example.movielistapplication.MainActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class MovieListDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "movieList_Database";
    public static final String USER_TABLE = "user_Table";
    public static final String TAG = "MovieListDatabase";

    private static volatile MovieListDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;


    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

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


    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i(MovieListDatabase.TAG, "Created the Database!");
            databaseWriteExecutor.execute(() -> {
                UserDao dao = INSTANCE.userDAO();
                dao.deleteAll();
                User admin = new User("admin", "admin");
                admin.setAdmin(true);
                dao.insert(admin);

                User testUser = new User("testuser", "testuser");
                dao.insert(testUser);
            });
        }
    };

    public abstract UserDao userDAO();



}
