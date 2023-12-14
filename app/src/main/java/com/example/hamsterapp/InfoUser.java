package com.example.hamsterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.hamsterapp.ModelsRETROFIT.UpdateUserData;
import com.example.hamsterapp.ModelsRETROFIT.UserData;
import com.example.hamsterapp.ViewModelss.InfoUserViewModel;

public class InfoUser extends AppCompatActivity {
    private InfoUserViewModel infoUserViewModel;
    private InfoUserViewModel viewModel;
    private Button btnCambiar, btnCerrar;
    private EditText editTextNombre, editTextApellidoPaterno, editTextApellidoMaterno;
    private String obtenerNombreCompleto(UserData userData) {
        return userData.getName() + " " + userData.getApP() + " " + userData.getApM();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);
        infoUserViewModel = new ViewModelProvider(this).get(InfoUserViewModel.class);


        btnCambiar = findViewById(R.id.cambiar);
        btnCerrar = findViewById(R.id.cerrar);
        editTextNombre = findViewById(R.id.ename);
        editTextApellidoPaterno = findViewById(R.id.pass1);
        editTextApellidoMaterno = findViewById(R.id.pass2);
        TextView txtNombre = findViewById(R.id.txtNombre);
        txtNombre.setText("");

        ImageView imageViewAtras = findViewById(R.id.patras);
        imageViewAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoUser.this, TimerUserJaulas.class);
                startActivity(intent);
            }
        });
        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateUserData updateUserData = obtenerDatosUsuario();
                infoUserViewModel.actualizarUsuario(updateUserData);
                editTextNombre.setText("");
                editTextApellidoPaterno.setText("");
                editTextApellidoMaterno.setText("");
            }
        });

        infoUserViewModel.getUpdateSuccessLiveData().observe(this, updateSuccess -> {
            if (updateSuccess) {
                Toast.makeText(InfoUser.this, "Usuario actualizado con éxito", Toast.LENGTH_SHORT).show();
            }
        });

        infoUserViewModel.getUserDataLiveData().observe(this, userData -> {
            if (userData != null) {
                String nombreCompleto = obtenerNombreCompleto(userData);
                txtNombre.setText(nombreCompleto);
                // Actualiza los EditText con los valores correspondientes
                editTextNombre.setText(userData.getName());
                editTextApellidoPaterno.setText(userData.getApP());
                editTextApellidoMaterno.setText(userData.getApM());
            }
        });

        infoUserViewModel.getErrorMessage().observe(this, errorMessage -> {
            Toast.makeText(InfoUser.this, errorMessage, Toast.LENGTH_SHORT).show();
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoUserViewModel.cerrarSesion();
                Intent intent = new Intent(InfoUser.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        infoUserViewModel.getErrorMessage().observe(this, errorMessage -> {
            Toast.makeText(InfoUser.this, errorMessage, Toast.LENGTH_SHORT).show();
        });

        infoUserViewModel.setInfoo(InfoUser.this);
        infoUserViewModel.getNam().observe(this, name -> {
            editTextNombre.setText(name);
        });
        infoUserViewModel.getApP().observe(this, apP -> {
            editTextApellidoPaterno.setText(apP);
        });
        infoUserViewModel.getApM().observe(this, apM -> {
            editTextApellidoMaterno.setText(apM);
        });
        infoUserViewModel.getNom().observe(this, nombre -> {
            txtNombre.setText(nombre);
        });
        }

    private UpdateUserData obtenerDatosUsuario() {
        String nombre = editTextNombre.getText().toString();
        String apellidoPaterno = editTextApellidoPaterno.getText().toString();
        String apellidoMaterno = editTextApellidoMaterno.getText().toString();

        return new UpdateUserData(nombre, apellidoPaterno, apellidoMaterno);
    }
}