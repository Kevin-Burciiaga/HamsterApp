package com.example.hamsterapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.Models.Jaula;
import com.example.hamsterapp.ModelsRETROFIT.ApiResponse;
import com.example.hamsterapp.ModelsRETROFIT.UpdateUserData;
import com.example.hamsterapp.ModelsRETROFIT.UserData;
import com.example.hamsterapp.SharedPreferences.Token;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoUser extends AppCompatActivity {
    Button btnCambiar, btnCerrar;
    EditText editTextNombre, editTextApellidoPaterno, editTextApellidoMaterno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);

        btnCambiar = findViewById(R.id.cambiar);
        btnCerrar = findViewById(R.id.cerrar);
        editTextNombre = findViewById(R.id.ename);
        editTextApellidoPaterno = findViewById(R.id.pass1);
        editTextApellidoMaterno = findViewById(R.id.pass2);
        TextView txtNombre = findViewById(R.id.txtNombre);
        txtNombre.setText("");

        ImageView imageViewAtras = findViewById(R.id.patras);
        imageViewAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia la actividad Jaula cuando se hace clic en la ImageView
                Intent intent = new Intent(InfoUser.this, Jaula.class);
                startActivity(intent);
            }
        });
        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarUsuario();
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        });


    }

    private void cerrarSesion() {
        String token = Token.getToken(InfoUser.this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.135.187.40/api/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<Void> call = jsonPlaceHolderApi.logout("Bearer " + token);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Token.clearToken(InfoUser.this);
                    Token.saveIsLoggedIn(InfoUser.this, false);

                    Intent intent = new Intent(InfoUser.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("InfoUser", "Error en onResponse: " + errorBody);
                        Toast.makeText(InfoUser.this, "Error al cerrar sesión: " + errorBody, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(InfoUser.this, "Error en la solicitud de cierre de sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actualizarUsuario() {
        String nombre = editTextNombre.getText().toString();
        String apellidoPaterno = editTextApellidoPaterno.getText().toString();
        String apellidoMaterno = editTextApellidoMaterno.getText().toString();


        UpdateUserData updateUserData = new UpdateUserData(nombre, apellidoPaterno, apellidoMaterno);

        String token = Token.getToken(InfoUser.this);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.135.187.40/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<ApiResponse> call = jsonPlaceHolderApi.updateUser("Bearer " + token, updateUserData);



        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {

                    ApiResponse apiResponse = response.body();
                    UserData userData = apiResponse.getData();

                    // Obtén el nuevo nombre completo del usuario
                    String nuevoNombreCompleto = obtenerNombreCompleto(userData);

                    // Actualiza el TextView directamente
                    TextView txtNombre = findViewById(R.id.txtNombre);
                    txtNombre.setText(nuevoNombreCompleto);

                    editTextNombre.setText("");
                    editTextApellidoPaterno.setText("");
                    editTextApellidoMaterno.setText("");
                    Toast.makeText(InfoUser.this, "Usuario actualizado con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("InfoUser", "Error en onResponse: " + errorBody);
                        Toast.makeText(InfoUser.this, "Error en la actualización: " + errorBody, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }



            private String obtenerNombreCompleto(UserData userData) {

                return userData.getName() + " " + userData.getApP() + " " + userData.getApM();
            }


            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(InfoUser.this, "Error en la solicitud de actualización", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
