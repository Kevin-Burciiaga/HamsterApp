package com.example.hamsterapp;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.hamsterapp.InterfaceRETROFIT.JsonPlaceHolderApi;
import com.example.hamsterapp.ModelsRETROFIT.SensorData;
import com.example.hamsterapp.RetrofitSingletonn.Singleton;
import com.example.hamsterapp.SharedPreferences.Token;
import com.example.hamsterapp.ViewModelss.Activity1ViewModel;

import java.util.List;

public class Activity1Activity extends AppCompatActivity {

    private Activity1ViewModel viewModel;
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


        viewModel = new ViewModelProvider(this).get(Activity1ViewModel.class);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 60000);
            }
        }, 60000);

        String token = Token.getToken(Activity1Activity.this);
        int idJaula = 1;

        JsonPlaceHolderApi jsonPlaceHolderApi = Singleton.getRetrofitInstance().create(JsonPlaceHolderApi.class);

        viewModel.fetchSensorData(idJaula, token);

        viewModel.getSensorDataList().observe(this, sensorDataList -> {
            updateUI(sensorDataList);
        });

        viewModel.getErrorMessage().observe(this, errorMessage -> {
            Toast.makeText(Activity1Activity.this, errorMessage, Toast.LENGTH_SHORT).show();
        });
    }

    private void updateUI(List<SensorData> sensorDataList) {
        for (SensorData sensorData : sensorDataList) {
            switch (sensorData.getSensor()) {
                case "temperatura":
                    txtTemp.setText(sensorData.getLastValue() + "°C");
                    break;
                case "humedad":
                    txtHum.setText(sensorData.getLastValue() + "%");
                    break;
                case "luzsensor":
                    txtlu.setText(sensorData.getLastValue());
                    break;
                case "rpm":
                    try {
                        double rpmValue = Double.parseDouble(sensorData.getLastValue());
                        txtVel.setText(rpmValue + "rpm");
                    } catch (NumberFormatException e) {
                        Log.w("API Response", "Error al convertir a double: " + sensorData.getLastValue());
                    }
                case "movimien":
                    if (sensorData.getLastValue().equals("1")) {
                        txtmov.setText("Tu hamster se esta moviendo");
                    } else {
                        txtmov.setText("Sin movimiento");
                    }
                    break;
                case "ultrasonico":
                    txtuts.setText(sensorData.getLastValue() + "cm");
                    if (Double.parseDouble(sensorData.getLastValue()) > 4) {
                        Toast.makeText(this, "Llenar el dispensador del agua", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "infrarrojo":
                    if (sensorData.getLastValue().equals("1")) {
                        txtinf.setText("Infrarrojo detectado");
                    } else {
                        txtinf.setText("Sin infrarrojo");
                    }
                    break;
                default:
                    Log.w("API Response", "Sensor desconocido: " + sensorData.getSensor());
                    break;
            }
        }
    }

}
