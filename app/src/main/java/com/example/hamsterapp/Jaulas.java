package com.example.hamsterapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hamsterapp.ModelsRETROFIT.SensorData;

import com.example.hamsterapp.Adapters.JaulaAdapter;
import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.Models.Jaula;
import com.example.hamsterapp.ModelsRETROFIT.JaulasResponse;
import com.example.hamsterapp.SharedPreferences.Token;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Jaulas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Jaula> jaulaList;
    private JaulaAdapter jaulaAdapter;
    ImageView agregar, us, huella;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jaulas);

        recyclerView = findViewById(R.id.recyclerviewJaulas);
        agregar = findViewById(R.id.agregar);
        us = findViewById(R.id.us);
        huella = findViewById(R.id.huella);

        agregar.setOnClickListener(v -> {
            Intent i = new Intent(Jaulas.this, Agregar_Jaula.class);
            startActivity(i);
        });

        us.setOnClickListener(v -> {
            Intent i = new Intent(Jaulas.this, InfoUser.class);
            startActivity(i);
        });

        huella.setOnClickListener(v -> {
            Intent i = new Intent(Jaulas.this, Animales.class);
            startActivity(i);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        // Configurar el adaptador y escuchar clics en los elementos
        configurarAdapter();
    }

    private void configurarAdapter() {
        String token = Token.getToken(Jaulas.this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.135.187.40/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        // Obtener la lista de jaulas
        jsonPlaceHolderApi.getJaulas("Bearer " + token).enqueue(new Callback<JaulasResponse>() {
            @Override
            public void onResponse(Call<JaulasResponse> call, Response<JaulasResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getJaulas() != null) {
                    jaulaList = response.body().getJaulas();

                    // Configurar el adaptador
                    jaulaAdapter = new JaulaAdapter(getApplicationContext(), jaulaList);
                    recyclerView.setAdapter(jaulaAdapter);

                    Toast.makeText(Jaulas.this, "Jaulas cargadas correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    // Manejo de errores
                    Toast.makeText(Jaulas.this, "Error al cargar las jaulas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JaulasResponse> call, Throwable t) {
                // Manejo de errores
                Toast.makeText(Jaulas.this, "Error en la solicitud de jaulas", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
