package com.example.hamsterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = (TextView) findViewById(R.id.txtC);


      CountDownTimer countDownTimer = new CountDownTimer(5000,1000) {
          @Override
          public void onTick(long millisUntilFinished) {
              txt1.setText("" + millisUntilFinished/1000);
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