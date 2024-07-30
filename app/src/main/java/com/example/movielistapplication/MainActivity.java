package com.example.movielistapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;


import com.example.movielistapplication.Database.MovieListRepository;
import com.example.movielistapplication.Database.entities.User;

import com.example.movielistapplication.databinding.ActivityMainAdminLayoutBinding;
import com.example.movielistapplication.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_USER_ID = "com.example.movielistapplication.MAIN_ACTIVITY_USER_ID";
    private static final String SAVED_INSTANCE_STATE_USERID_KEY = "com.example.movielistapplication.SAVED_INSTANCE_STATE_USERID_KEY";


    private ActivityMainBinding binding;
    private ActivityMainAdminLayoutBinding adminBinding;
    private MovieListRepository repository;

    private static final int LOGGED_OUT = -1;
    private int loggedInUserId = -1;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        repository = MovieListRepository.getRepository(getApplication());
        loginUser(savedInstanceState); // call to method for logging in

        if (loggedInUserId == -1) {
            Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }
        // Keeps the user logged in
        updateSharedPreference();

        // Sets the action bar color and removes the title when it is created.
        if (getSupportActionBar() != null) {
            Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(
                    getResources().getColor(R.color.main_color)));
            getSupportActionBar().setTitle("");
        }


    }


    /**
     * Handles user login by attempting to retrieve the user ID from multiple sources.
     * Shared preferences, saved instance state, and the intent that started the activity.
     * If a valid user ID is found, it observes the user data from the repository and updates the UI.
     * If no user ID is found, it returns.
     */
    private void loginUser(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);
        loggedInUserId = sharedPreferences.getInt(getString(R.string.preference_userId_key), LOGGED_OUT);

        if (loggedInUserId == LOGGED_OUT & savedInstanceState != null && savedInstanceState.containsKey(SAVED_INSTANCE_STATE_USERID_KEY)) {
            loggedInUserId = savedInstanceState.getInt(SAVED_INSTANCE_STATE_USERID_KEY, LOGGED_OUT);
        }
        if (loggedInUserId == LOGGED_OUT) {
            loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);
        }
        if (loggedInUserId == LOGGED_OUT) {
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserId(loggedInUserId);
        userObserver.observe(this, user -> {
            this.user = user;
            if (this.user != null) {
                changeUIForUserLandingPage();
                invalidateOptionsMenu();
            }
        });
    }


    /**
     * Changes the UI based on the user's role.
     * If the user is an admin, they will see the admin layout.
     * If the user is not an admin, they will see the default user layout.
     */
    private void changeUIForUserLandingPage() {
        if (user.isAdmin()) {
            adminBinding = ActivityMainAdminLayoutBinding.inflate(getLayoutInflater());
            setContentView(adminBinding.getRoot());
            adminBinding.greetingTextView.setText("Hello, " + user.getUsername());
        }
        else {
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            binding.greetingTextView.setText("Hello, " + user.getUsername());
        }
    }





    /**
     * Updates the shared preference with the logged in user's id.
     * Stores the user id in the shared preference file to maintain the login state.
     * This allows the user to stay logged in even if the app is closed.
     */
    private void updateSharedPreference() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putInt(getString(R.string.preference_userId_key), loggedInUserId);
        sharedPrefEditor.apply();
    }


    /**
     * Factory method for creating an intent to start the MainActivity.
     * Adds the user ID as an extra to the intent so it can be used when starting.
     */
    static Intent mainActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }





}