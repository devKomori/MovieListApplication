package com.example.movielistapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import com.example.movielistapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);



    }

    /* Factory method to create an intent to go to the MainActivity */
    static Intent mainActivityIntentFactory(Context context){
        return new Intent(context, MainActivity.class);
    }




}