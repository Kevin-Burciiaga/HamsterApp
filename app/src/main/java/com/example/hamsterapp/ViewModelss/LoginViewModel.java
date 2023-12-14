package com.example.hamsterapp.ViewModelss;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.Jaulas;
import com.example.hamsterapp.ModelsRETROFIT.ApiResponse;
import com.example.hamsterapp.ModelsRETROFIT.LoginData;
import com.example.hamsterapp.RetrofitSingletonn.RetrofitSingleton;
import com.example.hamsterapp.SharedPreferences.Token;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<String> email = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public void setEmail(String emailValue) {
        email.setValue(emailValue);
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(String passwordValue) {
        password.setValue(passwordValue);
    }

public void login(Context context){
    String email = getEmail().getValue();
    String password = getPassword().getValue();

    LoginData loginData = new LoginData(email,password);
    Log.d("Login", "login: "+loginData.getEmail());
    Log.d("Login", "login: "+loginData.getPassword());
    Retrofit retrofit = RetrofitSingleton.getRetrofitInstance();
    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    Call<ApiResponse> call = jsonPlaceHolderApi.loginUser(loginData);

    call.enqueue(new Callback<ApiResponse>(){

        @Override
        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
            if (response.isSuccessful()){
                Toast.makeText(context, "Login exitoso", Toast.LENGTH_SHORT).show();
                ApiResponse apiResponse = response.body();
                String token = apiResponse.getToken();
                Token.saveIsLoggedIn(context,true);
                Token.saveToken(context,token);
                Intent i = new Intent(context, Jaulas.class);
                context.startActivity(i);
            }
            else {
                try {
                    String errorBody = response.errorBody().string();
                    Log.e("Registro", "Error en onResponse: " + errorBody);
                    if (errorBody.contains("Sin autorizacion, revise sus datos")) {
                        Toast.makeText(context, "Verifique sus datos", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context, "Activa tu cuenta", Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, "Se envio un correo de activación", Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        public void onFailure(Call<ApiResponse> call, Throwable t) {
            Toast.makeText(context, "Error en el login", Toast.LENGTH_SHORT).show();
            Log.e("LoginVM", "onFailure: "+t.getMessage() );
        }
    });

    }
}
