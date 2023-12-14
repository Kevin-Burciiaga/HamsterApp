package com.example.hamsterapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.hamsterapp.ViewModelss.AnimalesViewModel;

public class Animales extends AppCompatActivity {
    private AnimalesViewModel viewModel;
    Button btn1, btn2;
    ImageView atras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animales);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        atras = findViewById(R.id.atras);
        viewModel = new ViewModelProvider(this).get(AnimalesViewModel.class);
        atras.setOnClickListener(v -> {
            Intent i = new Intent(Animales.this, Jaulas.class);
            startActivity(i);
        });
        btn1.setOnClickListener(view -> {
            viewModel.ledss(1, Animales.this);
            btn1.setVisibility(view.INVISIBLE);
            btn2.setVisibility(view.VISIBLE);
            Toast.makeText(Animales.this, "Encendido", Toast.LENGTH_SHORT).show();
        });
        btn2.setOnClickListener(view -> {
            viewModel.ledss(0, Animales.this);
            btn2.setVisibility(view.INVISIBLE);
            btn1.setVisibility(view.VISIBLE);
            Toast.makeText(Animales.this, "Apagado", Toast.LENGTH_SHORT).show();
        });

    }


}