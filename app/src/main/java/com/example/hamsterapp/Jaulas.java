package com.example.hamsterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamsterapp.Adapters.JaulaAdapter;
import com.example.hamsterapp.Models.Jaula;

import java.util.ArrayList;


public class Jaulas extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<Jaula> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jaulas);
        recyclerView=findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList.add(new Jaula(R.drawable.jaula,"Jaula 1"));

        JaulaAdapter jaulasRecyclerView = new JaulaAdapter(this,arrayList);
        recyclerView.setAdapter(jaulasRecyclerView);


    }
}