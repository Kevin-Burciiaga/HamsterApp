package com.example.hamsterapp.InterfaceRETROFIT;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    String BASE_URL = "https://io.adafruit.com//api/v2/Emith14/";

    static ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }

    @Headers({
            "Content-Type: application/json",
            "X-AIO-Key: aio_HaqI42196mMramvIcFvflsv9ilvH"
    })
    @POST("feeds/led/data")
    Call<Void> led(@Body Integer data);

}
