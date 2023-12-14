package com.example.hamsterapp.ViewModelss;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.ModelsRETROFIT.SensorData;
import com.example.hamsterapp.RetrofitSingletonn.Singleton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity1ViewModel extends ViewModel {
    private MutableLiveData<List<SensorData>> sensorDataList = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public void fetchSensorData(int idJaula, String token) {
        JsonPlaceHolderApi jsonPlaceHolderApi = Singleton.getJsonPlaceHolderApi();

        Call<List<SensorData>> call = jsonPlaceHolderApi.getSensorData(idJaula, token);
        call.enqueue(new Callback<List<SensorData>>() {
            @Override
            public void onResponse(Call<List<SensorData>> call, Response<List<SensorData>> response) {
                if (response.isSuccessful()) {
                    sensorDataList.setValue(response.body());
                } else {
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<SensorData>> call, Throwable t) {
                errorMessage.setValue("Error in API call");
            }
        });
    }

    public MutableLiveData<List<SensorData>> getSensorDataList() {
        return sensorDataList;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
