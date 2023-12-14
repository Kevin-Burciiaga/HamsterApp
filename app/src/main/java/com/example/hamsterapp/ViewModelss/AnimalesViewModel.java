package com.example.hamsterapp.ViewModelss;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.ModelsRETROFIT.ApagarLed;
import com.example.hamsterapp.ModelsRETROFIT.LedsData;
import com.example.hamsterapp.RetrofitSingletonn.Singleton;
import com.example.hamsterapp.SharedPreferences.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AnimalesViewModel extends ViewModel {

private MutableLiveData <Integer> led_value = new MutableLiveData<>();

    public MutableLiveData<Integer> getLed_value() {
        return led_value;
    }

    public void setLed_value(MutableLiveData<Integer> led_value) {
        this.led_value = led_value;
    }

    public void ledss(int led_value, Context context){
        LedsData ledsData = new LedsData(led_value);
        String estado="Encendido";
        String token = Token.getToken(context);
        Retrofit retrofit = Singleton.getRetrofitInstance();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<LedsData> call = jsonPlaceHolderApi.getLeds(ledsData);
        call.enqueue(new Callback<LedsData>() {
            @Override
            public void onResponse(Call<LedsData> call, Response<LedsData> response) {
                if (response.isSuccessful()){
                    LedsData ledsData1 = response.body();
                    setLed_value(new MutableLiveData<>(ledsData1.getLed_value()));
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        String error = response.errorBody().string();
                        Log.d("Error", "onResponse: "+error);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LedsData> call, Throwable t) {
                Log.d("Error", "onFailure: "+t.getMessage());
            }
        });
    }

    public void setdata(int led_value){
        Retrofit retrofit = Singleton.getRetrofitInstance();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<ApagarLed> call = jsonPlaceHolderApi.getLeds(new ApagarLed(led_value));
        call.enqueue(new Callback<ApagarLed>() {
            @Override
            public void onResponse(Call<ApagarLed> call, Response<ApagarLed> response) {
                if (response.isSuccessful()){
                    ApagarLed apagarLed = response.body();
                    setLed_value(new MutableLiveData<>(apagarLed.getLed_value()));
                }
                else {
                    try {
                        String error = response.errorBody().string();
                        Log.d("Error", "onResponse: "+error);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApagarLed> call, Throwable t) {
                Log.d("Error", "onFailure: "+t.getMessage());
            }
        });
    }

}
