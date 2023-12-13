package com.example.hamsterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.hamsterapp.SharedPreferences.Token;
import com.example.hamsterapp.ViewModelss.LoginViewModel;

public class Login extends AppCompatActivity {
private LoginViewModel loginViewModel;
Button btn1, btn2;
EditText etEmail, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn1 = (Button) findViewById(R.id.btnreg);
        btn2 = (Button) findViewById(R.id.btnLogin);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        revisarSesion();
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Registro.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()){
                    etEmail.setError("Llene el campo de correo");
                    etPassword.setError("Llene el campo de contraseña");
                    Toast.makeText(getApplicationContext(), "Llene todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginViewModel.setEmail(etEmail.getText().toString());
                loginViewModel.setPassword(etPassword.getText().toString());
                loginViewModel.login(Login.this);
            }
        });

    }

    public void revisarSesion(){
        if (Token.getIsLoggedIn(Login.this)){
            Intent intent = new Intent(Login.this,Jaulas.class);
            startActivity(intent);
            finish();
        }
    }
}