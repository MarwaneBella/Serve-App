package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText etUsername = (EditText) findViewById(R.id.TextUsername);
        final EditText etEmail = (EditText) findViewById(R.id.TextEmail);
        final EditText etPassword = (EditText) findViewById(R.id.TextPassword);
        final Button bRegister = (Button) findViewById(R.id.Button);

        bRegister.setOnClickListener(v -> {
            final String username = etUsername.getText().toString();
            final String email = etEmail.getText().toString();
            final String password = etPassword.getText().toString();
            if(!username.isEmpty() &&  !email.isEmpty() && !password.isEmpty()){

                Intent intent = new Intent(Register.this, Login.class);
                intent.putExtra("email",email);
                intent.putExtra("password",password);
                Register.this.startActivity(intent);
            }


        });
    }
}