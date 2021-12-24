package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class EditMyService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_service);
        View rootView = findViewById(android.R.id.content).getRootView();


        ImageButton imageButton = findViewById(R.id.imageButton);
        String[] items =  getResources().getStringArray(R.array.category1);
        SelectCategory select = new SelectCategory(rootView,items);
        select.setItems();


        imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(EditMyService.this, MainActivity.class);
            intent.putExtra("id",2+"");
            EditMyService.this.startActivity(intent);
        });
    }
}