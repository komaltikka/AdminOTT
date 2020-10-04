package com.example.adminott.services;

import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClient {


    public static Retrofit getApiClient()
    {



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://myleader.sparsematrix.co.in/ott/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }



}


