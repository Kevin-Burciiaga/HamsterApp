package com.example.hamsterapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.ModelsRETROFIT.SensorData;
import com.example.hamsterapp.SharedPreferences.Token;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity1Activity extends AppCompatActivity {
    private TextView txtTemp, txtHum, txtVel, txtlu, txtinf, txtmov, txtuts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);


        txtTemp = findViewById(R.id.txtTemp);
        txtHum = findViewById(R.id.txtHum);
        txtVel = findViewById(R.id.txtVel);
        txtlu = findViewById(R.id.txtlu);
        txtinf = findViewById(R.id.txtinf);
        txtmov = findViewById(R.id.txtmov);
        txtuts = findViewById(R.id.txtuts);


        String token = Token.getToken(Activity1Activity.this);

        int idJaula = 1;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.135.187.40/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build();


        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        Call<List<SensorData>> call = jsonPlaceHolderApi.getSensorData(idJaula, token);
        call.enqueue(new Callback<List<SensorData>>() {
            @Override
            public void onResponse(Call<List<SensorData>> call, Response<List<SensorData>> response) {
                if (!response.isSuccessful()) {
                    Log.e("API Response", "Error: " + response.code());
                    return;
                }
                List<SensorData> sensorDataList = response.body();

                for (SensorData sensorData : sensorDataList) {
                    switch (sensorData.getSensor()) {
                        case "temperatura":
                            txtTemp.setText(sensorData.getLastValue());
                            break;
                        case "humedad":
                            txtHum.setText(sensorData.getLastValue());
                            break;
                        case "luzsensor":
                            txtlu.setText(sensorData.getLastValue());
                            break;
                        case "rpm":
                            try {
                                double rpmValue = Double.parseDouble(sensorData.getLastValue());

                                double calculatedValue = (rpmValue * 10 * 3.1416) / (1000 * 60) * 60;

                                txtVel.setText(String.valueOf(calculatedValue));
                            } catch (NumberFormatException e) {
                                Log.e("Activity1Activity", "Error al convertir el valor RPM a número");
                                txtVel.setText("Error");
                            }
                            break;
                        case "movimien":
                            txtmov.setText(sensorData.getLastValue());
                            break;
                        case "ultrasonico":
                            txtuts.setText(sensorData.getLastValue());
                            break;
                        case "infrarrojo":
                            txtinf.setText(sensorData.getLastValue());
                            break;
                        default:
                            Log.w("API Response", "Sensor desconocido: " + sensorData.getSensor());
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SensorData>> call, Throwable t) {
                Toast.makeText(Activity1Activity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.e("API Failure", "Error in API call", t);
            }
        });
    }
}
