package com.example.movielistapplication.Database.DAOS;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.movielistapplication.Database.MovieListDatabase;
import com.example.movielistapplication.Database.entities.User;

import java.util.List;


/*Data Access Object (DAO) for the User entity.*/
@Dao
public interface UserDao {

    /* Insert a data into the database and handle conflicts. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);


    @Delete
    void delete(User user);


    @Query("DELETE from " + MovieListDatabase.USER_TABLE)
    void deleteAll();


    /* Query to get all users in the database. */
    @Query("SELECT * FROM " + MovieListDatabase.USER_TABLE + " ORDER BY username")
    LiveData<List<User>> getAllUsers();


    /* Query to get a user by their username */
    @Query("SELECT * FROM " + MovieListDatabase.USER_TABLE + " WHERE username == :username")
    LiveData<User> getUserByUserName(String username);


    /* Query to get a user by their user id */
    @Query("SELECT * FROM " + MovieListDatabase.USER_TABLE + " WHERE userId == :userId")
    LiveData<User> getUserByUserId(int userId);

}
