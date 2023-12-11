package com.example.hamsterapp.InterfaceRETROFIT;

import com.example.hamsterapp.ModelsRETROFIT.AnimalResponse;
import com.example.hamsterapp.ModelsRETROFIT.ApiResponse;
import com.example.hamsterapp.ModelsRETROFIT.LoginData;
import com.example.hamsterapp.ModelsRETROFIT.RegistroData;
import com.example.hamsterapp.ModelsRETROFIT.UpdateUserData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface JsonPlaceHolderApi {

    @POST("register")
    Call<ApiResponse> registerUser(@Body RegistroData registroData);

    @POST("login")
    Call<ApiResponse> loginUser(@Body LoginData loginDataData);

    @GET("animales")
    Call<AnimalResponse> getAnimals();

    @PUT("users/update")
    Call<ApiResponse> updateUser(@Header("Authorization") String token, @Body UpdateUserData updateUserData);
}
