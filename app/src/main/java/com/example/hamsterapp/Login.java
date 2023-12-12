package com.example.hamsterapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.ModelsRETROFIT.ApiResponse;
import com.example.hamsterapp.ModelsRETROFIT.LoginData;
import com.example.hamsterapp.SharedPreferences.Token;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
Button btn1, btn2;
EditText etEmail, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn1 = (Button) findViewById(R.id.btnreg);
        btn2 = (Button) findViewById(R.id.btnLogin);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Registro.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });

    }
    public void login(View view){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (email.isEmpty() || password.isEmpty()){
            etEmail.setError("Llene el campo de correo");
            etPassword.setError("Llene el campo de contraseña");
            Toast.makeText(getApplicationContext(), "Llene todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        LoginData loginData = new LoginData(email,password);
        Log.d("Login", "login: "+loginData.getEmail());
        Log.d("Login", "login: "+loginData.getPassword());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.135.187.40/api/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<ApiResponse> call = jsonPlaceHolderApi.loginUser(loginData);

        call.enqueue(new Callback<ApiResponse>(){

            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Login exitoso", Toast.LENGTH_SHORT).show();
                    ApiResponse apiResponse = response.body();
                    String token = apiResponse.getToken();
                    Token.saveToken(Login.this,token);
                    Intent intent = new Intent(Login.this,Jaulas.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("Registro", "Error en onResponse: " + errorBody);
                       if (errorBody.contains("Sin autorizacion, revise sus datos")) {
                            Toast.makeText(Login.this, "Verifique sus datos", Toast.LENGTH_SHORT).show();
                        }

                       else {
                           Toast.makeText(Login.this, "Error en el registro: " + errorBody, Toast.LENGTH_SHORT).show();
                       }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error en el login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}