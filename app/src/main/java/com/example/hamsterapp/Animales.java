package com.example.hamsterapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamsterapp.Adapters.AnimalesAdapter;
import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.Models.Animal;
import com.example.hamsterapp.ModelsRETROFIT.AnimalResponse;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Animales extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Animal> animalList;
    private AnimalesAdapter animalesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animales);
        recyclerView=findViewById(R.id.recyclerviewA);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclervieww();
    }

    public void recyclervieww(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.135.187.40/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        jsonPlaceHolderApi.getAnimals().enqueue(new Callback<AnimalResponse>() {
            @Override
            public void onResponse(Call<AnimalResponse> call, Response<AnimalResponse> response) {
                if (response.isSuccessful()){
                    animalList = response.body().getAnimals();
                    animalesAdapter = new AnimalesAdapter(getApplicationContext(),animalList);
                    recyclerView.setAdapter(animalesAdapter);
                }
                else {
                    try{
                        String error = response.errorBody().string();
                        Toast.makeText(Animales.this,  error, Toast.LENGTH_SHORT).show();
                    }
                     catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AnimalResponse> call, Throwable t) {
                Toast.makeText(Animales.this,  "Error", Toast.LENGTH_SHORT).show();
                Log.e("Animales", "Error: " + t.getMessage());
            }
        });

    }
}