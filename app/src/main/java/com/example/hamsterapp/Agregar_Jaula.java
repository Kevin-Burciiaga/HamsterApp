package com.example.hamsterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.hamsterapp.ViewModelss.Agregar_JualaViewModel;

public class Agregar_Jaula extends AppCompatActivity {
private Agregar_JualaViewModel model;
Spinner spinner;
EditText ETnombre;
private String opcionSeleccionada;
private String selectedItem;
Button btn1;
ImageView atras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_jaula);
        atras = findViewById(R.id.atras);
        ETnombre = findViewById(R.id.nombre);
        spinner = findViewById(R.id.spinner);
        btn1 = findViewById(R.id.btn1);
        model = new ViewModelProvider(this).get(Agregar_JualaViewModel.class);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Agregar_Jaula.this, Jaulas.class);
                startActivity(i);
            }
        });

        String[] opciones = {"Hamster", "Raton", "Cuyo", "Hurón"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ETnombre.getText().toString().isEmpty()){
                    ETnombre.setError("Llene el campo de nombre");
                    return;
                }
                agregarJaulas(view);
            }
        });
    }

    public void agregarJaulas(View view){
        switch (selectedItem){
            case "Hamster":
                opcionSeleccionada = "1";
                break;
            case "Raton":
                opcionSeleccionada = "2";
                break;
            case "Cuyo":
                opcionSeleccionada = "3";
                break;
            case "Hurón":
                opcionSeleccionada = "4";
                break;
            default:
                opcionSeleccionada = "1";
                break;
        }
        model.setNombre(ETnombre.getText().toString());
        model.setTipo(opcionSeleccionada);
        model.agregarJaula(Agregar_Jaula.this);
        ETnombre.setText("");
        spinner.setSelection(0);
    }
}