package com.example.hamsterapp.ViewModelss;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.Models.Animal;
import com.example.hamsterapp.ModelsRETROFIT.AnimalResponse;
import com.example.hamsterapp.RetrofitSingletonn.Singleton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AnimalesViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Animal>> animalList = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public AnimalesViewModel(@NonNull Application application) {
        super(application);
        getAnimales();
    }

    public MutableLiveData<List<Animal>> getAnimalList() {
        return animalList;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void getAnimales(){
        Retrofit retrofit = Singleton.getRetrofitInstance();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        jsonPlaceHolderApi.getAnimals().enqueue(new Callback<AnimalResponse>() {
            @Override
            public void onResponse(Call<AnimalResponse> call, Response<AnimalResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getAnimals() != null) {
                    animalList.setValue(response.body().getAnimals());
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
            public void onFailure(Call<AnimalResponse> call, Throwable t) {
                errorMessage.setValue("Error en la solicitud de animales");
            }
        });

    }

}
