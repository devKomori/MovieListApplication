package com.example.movielistapplication.Database;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//https://api.themoviedb.org

public class ApiRetrofitClient {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}