
package com.example.hamsterapp.ViewModelss;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.ModelsRETROFIT.ApiResponse;
import com.example.hamsterapp.ModelsRETROFIT.RegistroData;
import com.example.hamsterapp.RetrofitSingletonn.RetrofitSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistroViewModel extends ViewModel {

    private MutableLiveData<String> registrationResult = new MutableLiveData<>();
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    public RegistroViewModel() {
        Retrofit retrofit = RetrofitSingleton.getRetrofitInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    public MutableLiveData<String> getRegistrationResult() {
        return registrationResult;
    }

    public void realizarRegistro(RegistroData registroData) {
        Call<ApiResponse> call = jsonPlaceHolderApi.registerUser(registroData);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    registrationResult.setValue("Registro exitoso");
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        registrationResult.setValue("Error en el registro: " + errorBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                registrationResult.setValue("Error en la conexión: " + t.getMessage());
            }
        });
    }
}

