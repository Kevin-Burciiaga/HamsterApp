
package com.example.hamsterapp.ViewModelss;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.ModelsRETROFIT.ApiResponse;
import com.example.hamsterapp.ModelsRETROFIT.UpdateUserData;
import com.example.hamsterapp.ModelsRETROFIT.UserData;
import com.example.hamsterapp.RetrofitSingletonn.RetrofitSingleton;
import com.example.hamsterapp.RetrofitSingletonn.Singleton;
import com.example.hamsterapp.SharedPreferences.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InfoUserViewModel extends AndroidViewModel {
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> updateSuccessLiveData = new MutableLiveData<>();
    private final MutableLiveData<UserData> userDataLiveData = new MutableLiveData<>();

    private final JsonPlaceHolderApi jsonPlaceHolderApi;

    public InfoUserViewModel(@NonNull Application application) {
        super(application);
        Retrofit retrofit = Singleton.getRetrofitInstance(); // Cambiado al Singleton específico para actualizar
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    public MutableLiveData<UserData> getUserDataLiveData() {
        return userDataLiveData;
    }

    public MutableLiveData<Boolean> getUpdateSuccessLiveData() {
        return updateSuccessLiveData;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void cerrarSesion() {
        String token = Token.getToken(getApplication());

        Retrofit retrofit = RetrofitSingleton.getRetrofitInstance();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Void> call = jsonPlaceHolderApi.logout("Bearer " + token);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Token.clearToken(getApplication());
                    Token.saveIsLoggedIn(getApplication(), false);
                    // Log aquí para verificar que se está ejecutando correctamente y que isLoggedIn es false
                    Log.d("InfoUserViewModel", "Sesión cerrada con éxito");
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        errorMessage.setValue(errorBody);
                        // Log aquí para registrar errores de cierre de sesión
                        Log.e("InfoUserViewModel", "Error en onResponse: " + errorBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Log aquí para registrar fallas en la llamada
                Log.e("InfoUserViewModel", "Error en la solicitud de cierre de sesión", t);
                errorMessage.setValue("Error en la solicitud de cierre de sesión");
            }
        });
    }


    public void actualizarUsuario(UpdateUserData updateUserData) {
        String token = Token.getToken(getApplication());

        Retrofit retrofit = Singleton.getRetrofitInstance(); // Cambiado al Singleton específico para actualizar
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
                } else {
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


