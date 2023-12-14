// Registro.java
package com.example.hamsterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.hamsterapp.ModelsRETROFIT.RegistroData;
import com.example.hamsterapp.ViewModelss.RegistroViewModel;

public class Registro extends AppCompatActivity {

    private RegistroViewModel registroViewModel;

    Button btnreg;
    EditText etNombre, etApPaterno, etApMaterno, etEmail, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        registroViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);

        btnreg = findViewById(R.id.reg);
        etNombre = findViewById(R.id.nombre);
        etApPaterno = findViewById(R.id.ap);
        etApMaterno = findViewById(R.id.am);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etConfirmPassword = findViewById(R.id.password_confirmation);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarRegistro();
            }
        });
    }

    private void realizarRegistro() {
        String nombre = etNombre.getText().toString();
        String apPaterno = etApPaterno.getText().toString();
        String apMaterno = etApMaterno.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        RegistroData registroData = new RegistroData(nombre, email, apPaterno, apMaterno, password, confirmPassword);

        registroViewModel.realizarRegistro(registroData);

        registroViewModel.getRegistrationResult().observe(this, result -> {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

            if (result.equals("Registro exitoso")) {
                Intent intent = new Intent(Registro.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
