package com.ziyata.crudapi.api;

import com.ziyata.crudapi.model.LoginBody;
import com.ziyata.crudapi.model.LoginResponse;
import com.ziyata.crudapi.model.SingelUserResponse;
import com.ziyata.crudapi.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

// TODO 4
public interface ApiInterface {

    // Membuat endpoint untuk merequest login
    @POST("/api/login")
    // Membuat method agar kita dapat melakukan operasi yang diinginkan
    Call<LoginResponse> postLogin(@Body LoginBody loginBody);

    // TODO 7
    // Membuat endpoint untuk mengambil resource user
    @GET ("/api/users?per_page=12")

    // Membuat method untuk digunakan untuk mengambil data dengan endpoint serta membawa parameter
    Call<UserResponse> getUser(@Query("per_page") int perPage);

    // endpoint untuk mengambil singel user
    @GET("/api/users/{id}")

    // Membuat method untuk mengambil data single user dengan mengirimkan id
    Call<SingelUserResponse> getSingelUser(@Path("id") int id);
}
