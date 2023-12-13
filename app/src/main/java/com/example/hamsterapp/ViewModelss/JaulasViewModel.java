package com.example.hamsterapp.ViewModelss;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.Models.Jaula;
import com.example.hamsterapp.ModelsRETROFIT.JaulasResponse;
import com.example.hamsterapp.RetrofitSingletonn.Singleton;
import com.example.hamsterapp.SharedPreferences.Token;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class JaulasViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Jaula>> jaulaList = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public JaulasViewModel(Application application) {
        super(application);
        getJaulas();
    }

    public MutableLiveData<List<Jaula>> getJaulaList() {
        return jaulaList;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
    public void getJaulas(){
        String token = Token.getToken(getApplication());
        Retrofit retrofit = Singleton.getRetrofitInstance();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        jsonPlaceHolderApi.getJaulas("Bearer " + token).enqueue(new Callback<JaulasResponse>(){

            @Override
            public void onResponse(Call<JaulasResponse> call, Response<JaulasResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getJaulas() != null) {
                    jaulaList.setValue(response.body().getJaulas());
                }
                else {
                    try {
                        String error = response.errorBody().string();
                        getErrorMessage().setValue(error);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<JaulasResponse> call, Throwable t) {
                errorMessage.setValue("Error en la solicitud de jaulas");
            }
        });
    }
}
