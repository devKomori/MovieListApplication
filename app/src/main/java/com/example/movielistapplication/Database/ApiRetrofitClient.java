package com.example.movielistapplication.Database;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//https://api.themoviedb.org

public class ApiRetrofitClient {

    private static Retrofit.Builder  retrofitbuilder = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofit = retrofitbuilder.build();

    private static final TMDBRequest tmdbRequest = retrofit.create(TMDBRequest.class);

    public static TMDBRequest getTmdbRequest(){

        return tmdbRequest;
    }

}