package com.example.movielistapplication.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Represents a User entity in the database.
 */

public @interface UserEntity {

    @Entity(tableName = "users")
    public class User {
        @PrimaryKey(autoGenerate = true)
        private int userId;
        private String username;
        private String password;
        private boolean isAdmin;
    }
}