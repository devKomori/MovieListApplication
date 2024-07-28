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

        /**
         * Constructor to create a User.
         */
        public User(String username, String password, boolean isAdmin) {
            this.username = username;
            this.password = password;
            this.isAdmin = isAdmin;
        }

        /**
         * Gets the user ID.
         */
        public int getUserId() {
            return userId;
        }

        /**
         * Sets the user ID.
         */
        public void setUserId(int userId) {
            this.userId = userId;
        }

        /**
         * Gets the username.
         */
        public String getUsername() {
            return username;
        }

        /**
         * Sets the username.
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * Gets the password.
         */
        public String getPassword() {
            return password;
        }

        /**
         * Sets the password.
         */
        public void setPassword(String password) {
            this.password = password;
        }

        /**
         * Checks if the user is an admin.
         */
        public boolean isAdmin() {
            return isAdmin;
        }

        /**
         * Sets the user as an admin.
         */
        public void setAdmin(boolean isAdmin) {
            this.isAdmin = isAdmin;
        }
    }

}