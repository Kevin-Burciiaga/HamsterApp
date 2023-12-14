package com.example.hamsterapp.ViewModelss;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.ModelsRETROFIT.InfoUsuario;
import com.example.hamsterapp.RetrofitSingletonn.RetrofitSingleton;
import com.example.hamsterapp.SharedPreferences.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InfoViewModel extends ViewModel {

    private final MutableLiveData<String> name = new MutableLiveData<>();
    private final MutableLiveData<String> apP = new MutableLiveData<>();
    private final MutableLiveData<String> apM = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();


    public MutableLiveData<String> getNam() {
        return name;
    }
    public MutableLiveData<String> getApP() {
        return apP;
    }

    public MutableLiveData<String> getApM() {
        return apM;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void setInfoo(Context context){
        String token = Token.getToken(context);
        Retrofit retrofit = RetrofitSingleton.getRetrofitInstance();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        jsonPlaceHolderApi.getUser("Bearer " + token).enqueue(new Callback<InfoUsuario>() {


            @Override
            public void onResponse(Call<InfoUsuario> call, Response<InfoUsuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    name.setValue(response.body().getName());
                    apP.setValue(response.body().getApP());
                    apM.setValue(response.body().getApM());
                }
                else {
                    try {
                        String error = response.errorBody().string();
                        errorMessage.setValue(error);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<InfoUsuario> call, Throwable t) {
                errorMessage.setValue("Error en la solicitud de animales");
                Log.e("Error", t.getMessage());
            }
        });
    }
}
