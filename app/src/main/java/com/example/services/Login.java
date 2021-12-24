package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText etEmail = (EditText) findViewById(R.id.TextEmail);
        final EditText etPassword = (EditText) findViewById(R.id.TextPassword);
        final Button bLogin = (Button) findViewById(R.id.btnLog);
        final TextView tvReg = (TextView) findViewById(R.id.TextReg);

        tvReg.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Register.class);
            Login.this.startActivity(intent);
        });

        bLogin.setOnClickListener(v -> {
            final String email = etEmail.getText().toString();
            final String password = etPassword.getText().toString();
            Intent intentt = new Intent(Login.this, MainActivity.class);
            Login.this.startActivity(intentt);

        });
    }
}