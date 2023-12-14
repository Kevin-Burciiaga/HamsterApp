package com.example.hamsterapp.InterfaceRETROFIT;

import com.example.hamsterapp.ModelsRETROFIT.AnimalResponse;
import com.example.hamsterapp.ModelsRETROFIT.ApiResponse;
import com.example.hamsterapp.ModelsRETROFIT.InfoUsuario;
import com.example.hamsterapp.ModelsRETROFIT.JaulaData;
import com.example.hamsterapp.ModelsRETROFIT.JaulasResponse;
import com.example.hamsterapp.ModelsRETROFIT.LedsData;
import com.example.hamsterapp.ModelsRETROFIT.LoginData;
import com.example.hamsterapp.ModelsRETROFIT.RegistroData;
import com.example.hamsterapp.ModelsRETROFIT.SensorData;
import com.example.hamsterapp.ModelsRETROFIT.UpdateUserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @POST("me")
    Call<InfoUsuario> getUser(@Header("Authorization") String token);

    @POST("register")
    Call<ApiResponse> registerUser(@Body RegistroData registroData);

    @POST("login")
    Call<ApiResponse> loginUser(@Body LoginData loginDataData);

    @GET("animales")
    Call<AnimalResponse> getAnimals();

    @POST("jaula")
    Call<ApiResponse> addJaula(@Header("Authorization") String token, @Body JaulaData jaulaData);

    @GET("jaula/user")
    Call<JaulasResponse>getJaulas(@Header("Authorization") String token);

    @PUT("users/update")
    Call<ApiResponse> updateUser(@Header("Authorization") String token, @Body UpdateUserData updateUserData);

    @GET("http/todos/{idJaula}")
    Call<List<SensorData>> getSensorData(
            @Path("idJaula") int idJaula, @Query("token") String token
    );

    @POST("logout")
    Call<Void> logout(@Header("Authorization") String token);

    @POST("http/encenderled")
    Call<LedsData> getLeds(@Header("Authorization") String token, @Body LedsData ledsData);
}

