package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class Profile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageButton imageButton = findViewById(R.id.imageButton);
        Intent intentt = getIntent();

        imageButton.setOnClickListener(v -> {

            Intent intent = new Intent(Profile.this, MainActivity.class);
            intent.putExtra("id",intentt.getStringExtra("id"));

            Profile.this.startActivity(intent);
        });


    }
}