package com.example.movielistapplication.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.movielistapplication.entities.UserEntity;

import java.util.List;

/**
 * Data Access Object (DAO) for the User entity.
 */
@Dao
public interface UserDao {
    /**
     * Inserts a new user into the database.
     */
    @Insert
    void insertUser(UserEntity.User user);

    /**
     * Retrieves a user by username and password.
     */
    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    UserEntity.User getUser(String username, String password);

    /**
     * Retrieves all users from the database.
     */
    @Query("SELECT * FROM users")
    LiveData<List<UserEntity.User>> getAllUsers();
}
