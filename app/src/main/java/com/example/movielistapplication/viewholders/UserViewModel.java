/*
package com.example.movielistapplication.viewholders;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;

import com.example.movielistapplication.Database.DAOS.UserDao;
import com.example.movielistapplication.Database.MovieListDatabase;
import com.example.movielistapplication.Database.entities.Movie;
import com.example.movielistapplication.Database.entities.User;

import java.util.List;
TODO this was commented out because the program cannot run otherwise.
    highlight all code and press ctrl + shift + / to uncomment

public class UserViewModel extends AndroidViewModel {

    private final UserDao userDao;
    private final LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        MovieListDatabase db = MovieListDatabase.getDatabase(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(User user) {
        MovieListDatabase.databaseWriteExecutor.execute(() -> userDao.insert(user));
    }

    public void update(User user) {
        MovieListDatabase.databaseWriteExecutor.execute(() -> userDao.update(user));
    }

    public void deleteUser(User user) {
        MovieListDatabase.databaseWriteExecutor.execute(() -> userDao.delete(user));
    }

    public void makeAdmin(User user) {
        user.setAdmin(true);
        update(user);
    }
}
*/
