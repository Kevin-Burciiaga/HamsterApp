package com.example.hamsterapp.RetrofitSingletonn;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singleton {
    private static final String BASE_URL = "http://3.135.187.40/api/v1/";
    private static Retrofit retrofitInstance;

    public static synchronized Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);

            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofitInstance;
    }

    public static JsonPlaceHolderApi getJsonPlaceHolderApi() {
        return getRetrofitInstance().create(JsonPlaceHolderApi.class);
    }
}
