package com.example.hamsterapp.InterfaceRETROFIT;

import com.example.hamsterapp.ModelsRETROFIT.ApiResponse;
import com.example.hamsterapp.ModelsRETROFIT.LoginData;
import com.example.hamsterapp.ModelsRETROFIT.RegistroData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @POST("register")
    Call<ApiResponse> registerUser(@Body RegistroData registroData);

    @POST("login")
    Call<ApiResponse> loginUser(@Body LoginData loginDataData);

}