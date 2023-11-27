package com.example.hamsterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Activity1Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Model> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        recyclerView=findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList.add(new Model(R.drawable.temperaturaa,"TEMPERATURA"));
        arrayList.add(new Model(R.drawable.humedad,"HUMEDAD"));
        arrayList.add(new Model(R.drawable.velocidad,"VELOCIDAD"));

        ModelRecyclerView modelRecyclerView = new ModelRecyclerView(this,arrayList);
        recyclerView.setAdapter(modelRecyclerView);



    }
}