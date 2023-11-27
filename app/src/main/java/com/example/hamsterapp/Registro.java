package com.example.hamsterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.ModelsRETROFIT.ApiResponse;
import com.example.hamsterapp.ModelsRETROFIT.RegistroData;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.widget.EditText;

import java.io.IOException;

public class Registro extends AppCompatActivity {

    Button btnreg;
    EditText etNombre, etApPaterno, etApMaterno, etEmail, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnreg = findViewById(R.id.reg);
        etNombre = findViewById(R.id.nombre);
        etApPaterno = findViewById(R.id.ap);
        etApMaterno = findViewById(R.id.am);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etConfirmPassword = findViewById(R.id.password_confirmation);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarRegistro();
            }
        });
    }

    private void realizarRegistro() {
        String nombre = etNombre.getText().toString();
        String apPaterno = etApPaterno.getText().toString();
        String apMaterno = etApMaterno.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        RegistroData registroData = new RegistroData(nombre, email, apPaterno, apMaterno, password, confirmPassword);
        Log.d("RegistroData", "name: " + registroData.getName());
        Log.d("RegistroData", "email: " + registroData.getEmail());
        Log.d("RegistroData", "ApP: " + registroData.getApP());
        Log.d("RegistroData", "ApM: " + registroData.getApM());
        Log.d("RegistroData", "password: " + registroData.getPassword());
        Log.d("RegistroData", "password_confirmation: " + registroData.getPasswordConfirmation());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.135.187.40/api/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<ApiResponse> call = jsonPlaceHolderApi.registerUser(registroData);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Registro.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("Registro", "Error en onResponse: " + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Registro.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                }
                }


            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Dentro del método onFailure
                Log.e("Registro", "Error en la conexión: " + t.getMessage(), t);
                Toast.makeText(Registro.this, "Error en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}