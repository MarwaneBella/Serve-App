package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class ServiceInfos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_infos);
        ImageButton imageButton = findViewById(R.id.imageButton);

        imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(ServiceInfos.this, MainActivity.class);
            intent.putExtra("id",1+"");
            ServiceInfos.this.startActivity(intent);
        });
    }

}