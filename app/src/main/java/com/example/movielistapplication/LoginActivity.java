package com.example.movielistapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.movielistapplication.Database.MovieListRepository;
import com.example.movielistapplication.Database.entities.User;
import com.example.movielistapplication.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private MovieListRepository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /* Gets instance of the repository in onCreate */
        repository = MovieListRepository.getRepository(getApplication());

        /* Set the onClickListener for the login button and calls the verifyUserLogin method */
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUserLogin();
            }
        });

    }


    /**
     * Verifies the user login by checking if the username exists and if the associated password
     * is correct. Displays toast messages to indicate various outcomes such as invalid password.
     */
    private void verifyUserLogin() {
        String password = binding.passwordLoginEditText.getText().toString();
        String username = binding.userLoginEditText.getText().toString();
        if (username.isEmpty()) {
            toastMaker("Username cannot be blank.");
            return;
        }
        if (password.isEmpty()) {
            toastMaker("Password cannot be blank.");
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, user -> {
            if (user != null) {
                if (password.equals(user.getPassword())) {
                    startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), user.getUserId()));
                }
                else {
                    toastMaker("Invalid password.");
                    binding.passwordLoginEditText.setSelection(0);
                }
            }
            else {
                toastMaker(String.format("%s does not exist.", username));
                binding.userLoginEditText.setSelection(0);
            }
        });

    }


    /* Method to create a toast message that displays information to the user */
    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    /* Factory method to create an intent to start the LoginActivity */
    static Intent loginIntentFactory(Context context){
        return new Intent(context, LoginActivity.class);
    }



}