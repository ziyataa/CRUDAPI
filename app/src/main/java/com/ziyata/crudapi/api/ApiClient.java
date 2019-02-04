package com.ziyata.crudapi.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//TODO 3
public class ApiClient {
    // Membuat object Retrofit
    private static  Retrofit retrofit = null;

    // Membuat method return getClient
    public static Retrofit getClient(){

        // Membuat object retrofit dan mensetting dengan baseUrl dan Converternya
        retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Kita kembalikan nilai retrofit yang sudah kita setting
        return retrofit;
    }
}
