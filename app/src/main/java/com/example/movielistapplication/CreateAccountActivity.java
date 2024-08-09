package com.example.movielistapplication;

import static com.example.movielistapplication.Database.MovieListDatabase.*;
import static com.example.movielistapplication.Database.MovieListRepository.repository;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movielistapplication.Database.DAOS.UserDao;
import com.example.movielistapplication.Database.MovieListDatabase;
import com.example.movielistapplication.Database.entities.User;
import com.example.movielistapplication.MainActivity;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        createAccountButton = findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void createAccount() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add user to the database
        MovieListDatabase db = MovieListDatabase.getInstance(this);
        UserDao userDao = db.userDao();
        User newUser = new User(username, password);
        repository.insertUser(newUser);

        // Navigate to the main page
        Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
