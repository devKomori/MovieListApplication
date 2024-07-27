package com.example.movielistapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movielistapplication.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        /*
         * Allows button to be pushed and sends you to the user's landing page.
         * TODO: temporary solution until we create users and database to verify user on login.
         */
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext());
                startActivity(intent);

            }
        });

    }


     /* Factory method to create an intent to go to the LoginActivity */
    static Intent loginIntentFactory(Context context){
        return new Intent(context, LoginActivity.class);
    }



}