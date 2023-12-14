package com.example.hamsterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class TimerUserJaulas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_user_jaulas);

        CountDownTimer countDownTimer = new CountDownTimer(2000,1000) {
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(TimerUserJaulas.this,Jaulas.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}