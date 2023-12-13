package com.example.hamsterapp.ViewModelss;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.ModelsRETROFIT.ApiResponse;
import com.example.hamsterapp.ModelsRETROFIT.JaulaData;
import com.example.hamsterapp.RetrofitSingletonn.Singleton;
import com.example.hamsterapp.SharedPreferences.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Agregar_JualaViewModel extends ViewModel {
private MutableLiveData <String> nombre = new MutableLiveData<>();
private MutableLiveData <String> id_animal = new MutableLiveData<>();

    public MutableLiveData<String> getNombre() {
        return nombre;
    }

    public void setNombre(String nombreValue) {
        nombre.setValue(nombreValue);
    }

    public MutableLiveData<String> getId_animal() {
        return id_animal;
    }

    public void setTipo(String id_animalValue) {
        id_animal.setValue(id_animalValue);
    }

    public void agregarJaula(Context context){
        String token = Token.getToken(context);
        String nombre = getNombre().getValue();
        String id_animal = getId_animal().getValue();
        JaulaData jaulaData = new JaulaData(nombre, id_animal);
        Retrofit retrofit = Singleton.getRetrofitInstance();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<ApiResponse> call = jsonPlaceHolderApi.addJaula("Bearer "+ token,jaulaData);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()){
                    ApiResponse apiResponse = response.body();
                    Toast.makeText(context, "Jaula Creada con exito", Toast.LENGTH_SHORT).show();
                    String mensaje = apiResponse.getMessage();
                    Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        String error = response.errorBody().string();
                        Log.e("Agregar_Jaula", "Error en onResponse: " + error);
                        if (error.contains("The name field is required.")) {
                            Toast.makeText(context, "Llene el campo nombre", Toast.LENGTH_SHORT).show();
                        }
                        if (error.contains("The id animal field is required.")) {
                            Toast.makeText(context, "Seleccione un animal", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "Error en el registro: " + error, Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e) {
                        Toast.makeText(context, "Error en el registro", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(context, "Error en la conexion", Toast.LENGTH_SHORT).show();
                Log.e("Agregar_Jaula", "Error: " + t.getMessage());
            }
        });
    }
}
