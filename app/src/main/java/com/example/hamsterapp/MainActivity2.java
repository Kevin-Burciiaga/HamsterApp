package com.example.hamsterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    ImageView img;
    TextView cant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        img=findViewById(R.id.img);
        cant=findViewById(R.id.cant);

        Intent intent = getIntent();
        int image = intent.getExtras().getInt("image");

        img.setImageResource(image);

    }
}