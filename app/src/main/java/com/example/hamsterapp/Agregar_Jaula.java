package com.example.hamsterapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.ModelsRETROFIT.ApiResponse;
import com.example.hamsterapp.ModelsRETROFIT.JaulaData;
import com.example.hamsterapp.SharedPreferences.Token;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Agregar_Jaula extends AppCompatActivity {
Spinner spinner;
EditText ETnombre;
private String opcionSeleccionada;
private String selectedItem;
Button btn1;
ImageView atras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_jaula);
        atras = findViewById(R.id.atras);
        ETnombre = findViewById(R.id.nombre);
        spinner = findViewById(R.id.spinner);
        btn1 = findViewById(R.id.btn1);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Agregar_Jaula.this, Jaulas.class);
                startActivity(i);
            }
        });

        String[] opciones = {"Hamster", "Raton", "Cuyo", "Hurón"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarJaula(view);
            }
        });
    }

    public void agregarJaula(View view){
        switch (selectedItem){
            case "Hamster":
                opcionSeleccionada = "1";
                break;
            case "Raton":
                opcionSeleccionada = "2";
                break;
            case "Cuyo":
                opcionSeleccionada = "3";
                break;
            case "Hurón":
                opcionSeleccionada = "4";
                break;
            default:
                opcionSeleccionada = "1";
                break;
        }
        String nombre = ETnombre.getText().toString();
        String id = opcionSeleccionada;
        if (nombre.isEmpty()){
            ETnombre.setError("Llene el campo nombre");
            Toast.makeText(getApplicationContext(), "Llene el campo nombre", Toast.LENGTH_SHORT).show();
            return;
        }
        JaulaData jaulaData = new JaulaData(nombre, id);
        Log.d("Agregar_Jaula", "agregarJaula: "+jaulaData.getName());
        Log.d("Agregar_Jaula", "agregarJaula: "+jaulaData.getAnimal_id());
        String token = Token.getToken(Agregar_Jaula.this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.135.187.40/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<ApiResponse> call = jsonPlaceHolderApi.addJaula("Bearer "+ token,jaulaData);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()){
                    ApiResponse apiResponse = response.body();
                    Toast.makeText(Agregar_Jaula.this, "Jaula Creada con exito", Toast.LENGTH_SHORT).show();
                    String mensaje = apiResponse.getMessage();
                    Toast.makeText(Agregar_Jaula.this, mensaje, Toast.LENGTH_SHORT).show();
                    ETnombre.setText("");
                    spinner.setSelection(0);
                }
                else {
                    try {
                        String error = response.errorBody().string();
                        Log.e("Agregar_Jaula", "Error en onResponse: " + error);
                        if (error.contains("The name field is required.")) {
                         Toast.makeText(Agregar_Jaula.this, "Llene el campo nombre", Toast.LENGTH_SHORT).show();
                        }
                        if (error.contains("The id animal field is required.")) {
                            Toast.makeText(Agregar_Jaula.this, "Seleccione un animal", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Agregar_Jaula.this, "Error en el registro: " + error, Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e) {
                        Toast.makeText(Agregar_Jaula.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(Agregar_Jaula.this, "Error en la conexion", Toast.LENGTH_SHORT).show();
                Log.e("Agregar_Jaula", "Error: " + t.getMessage());
            }
        });
    }
}