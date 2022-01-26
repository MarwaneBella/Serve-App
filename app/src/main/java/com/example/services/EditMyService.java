package com.example.services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditMyService extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    EditText etTitle,etLocation,etPrice,etDesc,etPhone;
    Button save,delete;
    String key;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_service);
        /*

        ImageButton imageButton = findViewById(R.id.imageButton);

        imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(EditMyService.this, MainActivity.class);
            intent.putExtra("id",2+"");
            EditMyService.this.startActivity(intent);
        });

        */

        Intent i = getIntent();
        key = i.getStringExtra("key");




        String[] items =  getResources().getStringArray(R.array.category1);
        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(EditMyService.this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);


        etTitle = findViewById(R.id.etTitle);
        etLocation = findViewById(R.id.etLocation);
        etPrice = findViewById(R.id.etPrice);
        etDesc = findViewById(R.id.etDesc);
        etPhone = findViewById(R.id.etPhone);

        save = findViewById(R.id.btnSave);
        delete = findViewById(R.id.btnDelete);

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();



        //show data
        showData();

        ///send data
        save.setOnClickListener(v-> {


            String category = autoCompleteTxt.getText().toString().trim();
            String titleService = etTitle.getText().toString().trim();
            String location = etLocation.getText().toString().trim();
            String price = etPrice.getText().toString().trim();
            String description = etDesc.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();


            if (titleService.isEmpty()) {
                etTitle.setError("Title is required!");
                etTitle.requestFocus();
                return;
            }
            if (location.isEmpty()) {
                etLocation.setError("Location is required!");
                etLocation.requestFocus();
                return;
            }
            if (price.isEmpty()) {
                etPrice.setError("Price is required!");
                etPrice.requestFocus();
                return;
            }
            if (description.isEmpty()) {
                etDesc.setError("Description is required!");
                etDesc.requestFocus();
                return;
            }
            if (phone.isEmpty()) {
                etPhone.setError("Phone is required!");
                etPhone.requestFocus();
                return;
            }

            Service service = new Service(category,titleService,location,price,description,phone);

            DatabaseReference servicesRef = FirebaseDatabase.getInstance().getReference("Services");
            servicesRef.child(userId).child(key)
                    .setValue(service).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(EditMyService.this, "Successfully!",Toast.LENGTH_LONG).show();
                        showData();

                    }
                    else {
                        Toast.makeText(EditMyService.this, "Failed!",Toast.LENGTH_LONG).show();
                    }
                }
            });

        });


        delete.setOnClickListener(v -> {

        });
    }





    public void showData(){
        DatabaseReference keyServiceRef = FirebaseDatabase.getInstance().getReference().child("Services").child(userId).child(key);
        keyServiceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Service service = snapshot.getValue(Service.class);
                autoCompleteTxt.setText(service.getCategory());
                etTitle.setText(service.getTitleService());
                etLocation.setText(service.getLocation());
                etPrice.setText(service.getPrice());
                etDesc.setText(service.getDescription());
                etPhone.setText(service.getPhone());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}