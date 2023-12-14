package com.example.hamsterapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



      CountDownTimer countDownTimer = new CountDownTimer(5000,1000) {
          @Override
          public void onTick(long millisUntilFinished) {
          }

          @Override
          public void onFinish() {
Intent intent = new Intent(MainActivity.this,Login.class);
startActivity(intent);
finish();
          }
      }.start();


    }
}