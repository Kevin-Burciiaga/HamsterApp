package com.example.hamsterapp.InterfaceRETROFIT;

import com.example.hamsterapp.ModelsRETROFIT.AnimalResponse;
import com.example.hamsterapp.ModelsRETROFIT.ApiResponse;
import com.example.hamsterapp.ModelsRETROFIT.JaulaData;
import com.example.hamsterapp.ModelsRETROFIT.JaulasResponse;
import com.example.hamsterapp.ModelsRETROFIT.LoginData;
import com.example.hamsterapp.ModelsRETROFIT.RegistroData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @POST("register")
    Call<ApiResponse> registerUser(@Body RegistroData registroData);

    @POST("login")
    Call<ApiResponse> loginUser(@Body LoginData loginDataData);

    @GET("animales")
    Call<AnimalResponse> getAnimals();

    @POST("jaula")
    Call<ApiResponse> addJaula(@Header("Authorization") String token, @Body JaulaData jaulaData);

    @GET("user")
    Call<JaulasResponse> getJaulas(@Header("Authorization") String token);
}