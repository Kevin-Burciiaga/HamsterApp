package com.example.hamsterapp.ViewModelss;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.ModelsRETROFIT.ApiResponse;
import com.example.hamsterapp.ModelsRETROFIT.UpdateUserData;
import com.example.hamsterapp.ModelsRETROFIT.UserData;
import com.example.hamsterapp.SharedPreferences.Token;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoUserViewModel extends AndroidViewModel {
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    private final MutableLiveData<Boolean> updateSuccessLiveData = new MutableLiveData<>();
    private final MutableLiveData<UserData> userDataLiveData = new MutableLiveData<>();


    public MutableLiveData<UserData> getUserDataLiveData() {
        return userDataLiveData;

    }
    public MutableLiveData<Boolean> getUpdateSuccessLiveData() {
        return updateSuccessLiveData;
    }

    public InfoUserViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void cerrarSesion() {
        String token = Token.getToken(getApplication());

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
                    Token.clearToken(getApplication());
                    Token.saveIsLoggedIn(getApplication(), false);
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        errorMessage.setValue(errorBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                errorMessage.setValue("Error en la solicitud de cierre de sesión");
            }
        });
    }

    public void actualizarUsuario(UpdateUserData updateUserData) {
        String token = Token.getToken(getApplication());

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
                if (!response.isSuccessful()) {
                    try {
                        String errorBody = response.errorBody().string();
                        errorMessage.setValue(errorBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    UserData userData = response.body().getData();
                    userDataLiveData.setValue(userData);
                    updateSuccessLiveData.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                errorMessage.setValue("Error en la solicitud de actualización");
            }
        });
    }

}

